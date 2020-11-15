package by.artempvn.hospital.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.dao.ColumnName;
import by.artempvn.hospital.model.dao.DiagnosisDao;
import by.artempvn.hospital.model.entity.DiagnosisData;
import by.artempvn.hospital.model.pool.ConnectionPool;

/**
 * The Class DiagnosisDaoImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DiagnosisDaoImpl implements DiagnosisDao {
	private static final String SQL_ADD_DIAGNOSIS = "INSERT INTO diagnosises"
			+ " (diagnosis_name,chosen_purpose_id_fk, established_by_doctor_id_fk,"
			+ " diagnosis_date) VALUES (?,?,?,?)";
	private static final String SQL_SELECT_DIAGNOSIS = "SELECT diagnosis_name,"
			+ "diagnosis_date, established_by_doctor_id_fk "
			+ "FROM diagnosises WHERE diagnosis_id=?";
	private static final String SQL_UPDATE_DIAGNOSIS = "UPDATE diagnosises JOIN"
			+ " patients ON diagnosis_id_fk=diagnosis_id SET diagnosis_name=?  "
			+ "WHERE patient_id=?";
	private static DiagnosisDaoImpl instance = new DiagnosisDaoImpl();

	private DiagnosisDaoImpl() {
	}

	public static DiagnosisDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long addDiagnosis(DiagnosisData data) throws DaoException {
		long id;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						SQL_ADD_DIAGNOSIS, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, data.getName());
			statement.setLong(2, data.getPurposeId());
			statement.setLong(3, Long.parseLong(data.getEstablishedDoctor()));
			statement.setLong(4, Long.parseLong(data.getDate()));
			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
			result.next();
			id = result.getLong(1);
		} catch (SQLException ex) {
			throw new DaoException("Failed to add diagnosis", ex);
		}
		return id;
	}

	@Override
	public DiagnosisData takeDiagnosis(long diagnosisId) throws DaoException {
		DiagnosisData diagnosis = new DiagnosisData();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_SELECT_DIAGNOSIS)) {
			statement.setLong(1, diagnosisId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString(ColumnName.DIAGNOSIS_NAME);
				String establishedDoctorId = resultSet
						.getString(ColumnName.ESTABLISHED_BY_DOCTOR_ID_FK);
				String date = resultSet.getString(ColumnName.DIAGNOSIS_DATE);
				diagnosis.setDiagnosisId(diagnosisId);
				diagnosis.setName(name);
				diagnosis.setEstablishedDoctor(establishedDoctorId);
				diagnosis.setDate(date);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take patient data", ex);
		}
		return diagnosis;
	}

	@Override
	public void updateDiagnosis(DiagnosisData diagnosisData) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_UPDATE_DIAGNOSIS)) {
			statement.setString(1, diagnosisData.getName());
			statement.setLong(2, diagnosisData.getPatientId());
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to update diagnosis", ex);
		}
	}

}
