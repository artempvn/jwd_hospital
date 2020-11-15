package by.artempvn.hospital.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.dao.ColumnName;
import by.artempvn.hospital.model.dao.UtilDao;
import by.artempvn.hospital.model.pool.ConnectionPool;

/**
 * The Class UtilDaoImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class UtilDaoImpl implements UtilDao {
	private static final int NOT_FOUND_ID = -1;
	private static final String SQL_ROLE = "SELECT role_type FROM roles";
	private static final String SQL_SPECIALITY = "SELECT speciality_type "
			+ "FROM specialities";
	private static final String SQL_ROLE_ID = "SELECT role_id FROM roles "
			+ "WHERE role_type=?";
	private static final String SQL_SPECIALITY_ID = "SELECT speciality_id "
			+ "FROM specialities WHERE speciality_type=?";
	private static final String SQL_STAFF_STATUS_ID = "SELECT staff_status_id "
			+ "FROM staff_statuses WHERE staff_status_type=?";
	private static final String SQL_PATIENT_STATUS_ID = "SELECT patient_status_id "
			+ "FROM patient_statuses WHERE patient_status_type=?";
	private static final String SQL_PROCEDURE_TYPE_ID = "SELECT procedure_type_id "
			+ "FROM procedure_types WHERE procedure_type=?";
	private static final String SQL_PROCEDURE_TYPES = "SELECT procedure_type "
			+ "FROM procedure_types";
	private static final String SQL_STAFF_ID_BY_LOGIN = "SELECT staff_id FROM staff"
			+ " WHERE login=?";
	private static final String SQL_PURPOSE_ID_BY_PATIENT_ID = "SELECT "
			+ "chosen_purpose_id_fk FROM diagnosises JOIN patients "
			+ "ON diagnosis_id_fk=diagnosis_id WHERE patient_id=?";
	private static final String SQL_PATIENT_ID_BY_PURPOSE_ID = "SELECT patient_id"
			+ " FROM patients JOIN diagnosises ON diagnosis_id_fk=diagnosis_id  "
			+ "WHERE chosen_purpose_id_fk=?";
	private static UtilDaoImpl instance = new UtilDaoImpl();

	private UtilDaoImpl() {
	}

	public static UtilDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<String> takeStaffRoles() throws DaoException {
		List<String> roles = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ROLE)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				roles.add(resultSet.getString(ColumnName.ROLE_TYPE));
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take staff roles", ex);
		}
		return roles;
	}

	@Override
	public List<String> takeStaffSpecialities() throws DaoException {
		List<String> specialities = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_SPECIALITY)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				specialities.add(resultSet.getString(ColumnName.SPECIALITY_TYPE));
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take staff specialities", ex);
		}
		return specialities;
	}

	@Override
	public String takeRoleId(String role) throws DaoException {
		String roleId = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_ROLE_ID)) {
			statement.setString(1, role);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				roleId = resultSet.getString(ColumnName.ROLE_ID);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take role ID", ex);
		}
		return roleId;
	}

	@Override
	public String takeSpecialityId(String speciality) throws DaoException {
		String specialityId = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_SPECIALITY_ID)) {
			statement.setString(1, speciality);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				specialityId = resultSet.getString(ColumnName.SPECIALITY_ID);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take speciality ID", ex);
		}
		return specialityId;
	}

	@Override
	public String takeStaffStatusId(String status) throws DaoException {
		String statusId = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_STAFF_STATUS_ID)) {
			statement.setString(1, status);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				statusId = resultSet.getString(ColumnName.STAFF_STATUS_ID);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take status ID", ex);
		}
		return statusId;
	}

	@Override
	public String takePatientStatusId(String status) throws DaoException {
		String statusId = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_PATIENT_STATUS_ID)) {
			statement.setString(1, status);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				statusId = resultSet.getString(ColumnName.PATIENT_STATUS_ID);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take status ID", ex);
		}
		return statusId;
	}

	@Override
	public String takeProcedureTypeId(String procedureType) throws DaoException {
		String procedureTypeId = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_PROCEDURE_TYPE_ID)) {
			statement.setString(1, procedureType);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				procedureTypeId = resultSet.getString(ColumnName.PROCEDURE_TYPE_ID);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take procedure type ID", ex);
		}
		return procedureTypeId;
	}

	@Override
	public List<String> takeProcedureTypes() throws DaoException {
		List<String> types = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_PROCEDURE_TYPES)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				types.add(resultSet.getString(ColumnName.PROCEDURE_TYPE));
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take procedure types", ex);
		}
		return types;
	}

	@Override
	public String takeStaffIdByLogin(String login) throws DaoException {
		String id = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_STAFF_ID_BY_LOGIN)) {
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getString(ColumnName.STAFF_ID);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take staff ID", ex);
		}
		return id;
	}

	@Override
	public long takePurposeIdByPatientId(long patientId) throws DaoException {
		long id = NOT_FOUND_ID;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_PURPOSE_ID_BY_PATIENT_ID)) {
			statement.setLong(1, patientId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getLong(ColumnName.CHOSEN_PURPOSE_ID_FK);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take purpose ID", ex);
		}
		return id;
	}

	@Override
	public long takePatientIdByPurposeId(long purposeId) throws DaoException {
		long id = NOT_FOUND_ID;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_PATIENT_ID_BY_PURPOSE_ID)) {
			statement.setLong(1, purposeId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getLong(ColumnName.PATIENT_ID);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take patient ID", ex);
		}
		return id;
	}

}
