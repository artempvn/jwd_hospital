package by.artempvn.hospital.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.dao.ColumnName;
import by.artempvn.hospital.model.dao.PurposeDao;
import by.artempvn.hospital.model.entity.DrugData;
import by.artempvn.hospital.model.entity.ProcedureData;
import by.artempvn.hospital.model.pool.ConnectionPool;

/**
 * The Class PurposeDaoImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class PurposeDaoImpl implements PurposeDao {
	private static final int PROCEDURE_OPERATION_ID = 2;
	private static final int NOT_FOUND_ID = -1;
	private static final String SQL_ADD_PURPOSE = "INSERT INTO purposes "
			+ "(attending_doctor_id_fk) VALUES (NULL)";
	private static final String SQL_ADD_DRUG = "INSERT INTO purposes_drugs "
			+ "(purpose_id_pd_fk, drug_id_fk) VALUES (?,?)";
	private static final String SQL_ADD_PROCEDURE = "INSERT INTO purposes_procedures"
			+ " (purpose_id_pp_fk, procedure_id_fk) VALUES (?,?)";
	private static final String SQL_CHANGE_ATTENDING_DOCTOR = "UPDATE purposes "
			+ "SET attending_doctor_id_fk=? WHERE purpose_id=?";
	private static final String SQL_FIND_PROCEDURE_TYPES = "SELECT procedure_type_id_fk"
			+ " FROM procedures JOIN purposes_procedures ON procedure_id=procedure_id_fk "
			+ "WHERE purpose_id_pp_fk=? ";
	private static final String SQL_DOCTOR_ID_BY_PURPOSE_ID = "SELECT "
			+ "attending_doctor_id_fk FROM purposes WHERE purpose_id=? ";
	private static final String SQL_REMOVE_ATTENDING_DOCTOR = "UPDATE purposes "
			+ "SET attending_doctor_id_fk=NULL WHERE purpose_id=?";
	private static final String SQL_SELECT_DRUGS = "SELECT drug_id, drug_name,"
			+ "amount,appointment_time FROM drugs JOIN purposes_drugs "
			+ "ON drug_id_fk=drug_id WHERE purpose_id_pd_fk=?";
	private static final String SQL_SELECT_PROCEDURES = "SELECT procedure_id, "
			+ "procedure_name,date_start,date_end,procedure_type FROM procedures "
			+ "JOIN procedure_types On procedure_type_id_fk=procedure_type_id  "
			+ "JOIN purposes_procedures ON procedure_id_fk=procedure_id "
			+ "WHERE purpose_id_pp_fk=? ORDER BY date_start, date_end";
	private static final String SQL_SELECT_PURPOSES_BY_DOCTOR = "SELECT purpose_id "
			+ "FROM purposes WHERE attending_doctor_id_fk=?";
	private static final String SQL_DELETE_DRUG = "DELETE FROM drugs WHERE drug_id=?";
	private static final String SQL_DELETE_PROCEDURE = "DELETE FROM procedures "
			+ "WHERE procedure_id=?";
	private static PurposeDaoImpl instance = new PurposeDaoImpl();

	private PurposeDaoImpl() {
	}

	public static PurposeDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long addPurpose() throws DaoException {
		long id;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						SQL_ADD_PURPOSE, Statement.RETURN_GENERATED_KEYS)) {
			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
			result.next();
			id = result.getLong(1);
		} catch (SQLException ex) {
			throw new DaoException("Failed to add purpose", ex);
		}
		return id;
	}

	@Override
	public void addDrugPurpose(long purposeId, long drugId) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_ADD_DRUG)) {
			statement.setLong(1, purposeId);
			statement.setLong(2, drugId);
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to add drug to purpose", ex);
		}
	}

	@Override
	public void addProcedurePurpose(long purposeId, long procedureId)
			throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_ADD_PROCEDURE)) {
			statement.setLong(1, purposeId);
			statement.setLong(2, procedureId);
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to add procedure to purpose", ex);
		}
	}

	@Override
	public void changeAttendingDoctor(String purposeId, long attendingDoctorId)
			throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_CHANGE_ATTENDING_DOCTOR)) {
			statement.setLong(1, attendingDoctorId);
			statement.setLong(2, Integer.valueOf(purposeId));
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to change attending doctor", ex);
		}
	}

	@Override
	public boolean isAnyOperationInPurpose(long purposeId) throws DaoException {
		boolean isAnyOperationInPurpose = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_FIND_PROCEDURE_TYPES)) {
			statement.setLong(1, purposeId);
			ResultSet result = statement.executeQuery();
			while (result.next() && !isAnyOperationInPurpose) {
				int typeId = result.getInt(ColumnName.PROCEDURE_TYPE_ID_FK);
				if (typeId == PROCEDURE_OPERATION_ID) {
					isAnyOperationInPurpose = true;
				}
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to add purpose", ex);
		}
		return isAnyOperationInPurpose;
	}

	@Override
	public long takeAttendingDoctorId(long purposeId) throws DaoException {
		long id = NOT_FOUND_ID;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_DOCTOR_ID_BY_PURPOSE_ID)) {
			statement.setLong(1, purposeId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getLong(ColumnName.ATTENDING_DOCTOR_ID_FK);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take attending doctor ID", ex);
		}
		return id;
	}

	@Override
	public void removeAttendingDoctor(long purposeId) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_REMOVE_ATTENDING_DOCTOR)) {
			statement.setLong(1, purposeId);
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to remove attending doctor", ex);
		}
	}

	@Override
	public List<DrugData> takeDrugs(long purposeId) throws DaoException {
		List<DrugData> drugs = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_SELECT_DRUGS)) {
			statement.setLong(1, purposeId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long drugId = resultSet.getLong(ColumnName.DRUG_ID);
				String name = resultSet.getString(ColumnName.DRUG_NAME);
				String amount = resultSet.getString(ColumnName.AMOUNT);
				String appointmentTime = resultSet
						.getString(ColumnName.APPOINTMENT_TIME);
				DrugData drug = new DrugData();
				drug.setDrugId(drugId);
				drug.setName(name);
				drug.setAmount(amount);
				drug.setAppointmentTime(appointmentTime);
				drugs.add(drug);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take patient data", ex);
		}
		return drugs;
	}

	@Override
	public List<ProcedureData> takeProcedures(long purposeId)
			throws DaoException {
		List<ProcedureData> procedures = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_SELECT_PROCEDURES)) {
			statement.setLong(1, purposeId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long procedureId = resultSet.getLong(ColumnName.PROCEDURE_ID);
				String name = resultSet.getString(ColumnName.PROCEDURE_NAME);
				String dateStart = resultSet.getString(ColumnName.DATE_START);
				String dateEnd = resultSet.getString(ColumnName.DATE_END);
				String type = resultSet.getString(ColumnName.PROCEDURE_TYPE);
				ProcedureData procedure = new ProcedureData();
				procedure.setProcedureId(procedureId);
				procedure.setName(name);
				procedure.setDateStart(dateStart);
				procedure.setDateEnd(dateEnd);
				procedure.setType(type);
				procedures.add(procedure);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take patient data", ex);
		}
		return procedures;
	}

	@Override
	public List<Long> takePurposeIdByAttendingDoctorId(long attendingDoctorId)
			throws DaoException {
		List<Long> purposes = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_SELECT_PURPOSES_BY_DOCTOR)) {
			statement.setLong(1, attendingDoctorId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long purposeId = resultSet.getLong(ColumnName.PURPOSE_ID);
				purposes.add(purposeId);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take patient data", ex);
		}
		return purposes;
	}

	@Override
	public boolean deleteDrugPurpose(long drugId) throws DaoException {
		int changedRows;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_DELETE_DRUG)) {
			statement.setLong(1, drugId);

			changedRows = statement.executeUpdate();
		} catch (SQLException ex) {
			throw new DaoException("Failed to delete drug", ex);
		}
		return (changedRows > 0);
	}

	@Override
	public boolean deleteProcedurePurpose(long procedureId) throws DaoException {
		int changedRows;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_DELETE_PROCEDURE)) {
			statement.setLong(1, procedureId);

			changedRows = statement.executeUpdate();
		} catch (SQLException ex) {
			throw new DaoException("Failed to delete procedure", ex);
		}
		return (changedRows > 0);
	}

}
