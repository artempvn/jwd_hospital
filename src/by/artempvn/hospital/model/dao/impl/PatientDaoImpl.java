package by.artempvn.hospital.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.dao.ColumnName;
import by.artempvn.hospital.model.dao.PatientDao;
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.pool.ConnectionPool;

/**
 * The Class PatientDaoImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class PatientDaoImpl implements PatientDao {
	private static final String SQL_ADD_PATIENT = "INSERT INTO patients"
			+ "(patient_name,patient_surname,patient_birthday,patient_status_id_fk,"
			+ "admission_date,admission_diagnosis,patient_second_name) "
			+ "VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_SELECT_PATIENTS = "SELECT patient_id, "
			+ "patient_name,patient_surname,patient_status_type,diagnosis_id_fk "
			+ "FROM patients JOIN patient_statuses "
			+ "ON patient_status_id_fk=patient_status_id "
			+ "ORDER BY patient_status_id_fk, patient_surname, patient_name";
	private static final String SQL_SELECT_PATIENT = "SELECT patient_name,"
			+ "patient_surname,patient_status_type,diagnosis_id_fk,"
			+ "patient_birthday,admission_date,admission_diagnosis,patient_second_name,"
			+ "treatment_result,discharge_date FROM patients JOIN patient_statuses "
			+ "ON patient_status_id_fk=patient_status_id WHERE patient_id=?";
	private static final String SQL_CHANGE_PATIENT_STATUS = "UPDATE patients "
			+ "SET patient_status_id_fk=? WHERE patient_id=?";
	private static final String SQL_ESTABLISH_DIAGNOSIS = "UPDATE patients "
			+ "SET diagnosis_id_fk=? WHERE patient_id=?";
	private static final String SQL_DISCHARGE_PATIENT = "UPDATE patients "
			+ "SET treatment_result=?, discharge_date=?,patient_status_id_fk=3 "
			+ "WHERE patient_id=?";
	private static PatientDaoImpl instance = new PatientDaoImpl();

	private PatientDaoImpl() {
	}

	public static PatientDaoImpl getInstance() {
		return instance;
	}

	@Override
	public void addPatient(PatientData data) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_ADD_PATIENT)) {
			statement.setString(1, data.getName());
			statement.setString(2, data.getSurname());
			statement.setLong(3, Long.parseLong(data.getDateBirth()));
			statement.setByte(4, Byte.parseByte(data.getStatus()));
			statement.setLong(5, Long.parseLong(data.getAdmissionDate()));
			statement.setString(6, data.getAdmissionDiagnosis());
			statement.setString(7, data.getSecondName());
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to add patient", ex);
		}
	}

	@Override
	public List<PatientData> takePatients() throws DaoException {
		List<PatientData> patients = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_SELECT_PATIENTS)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long id = resultSet.getLong(ColumnName.PATIENT_ID);
				String name = resultSet.getString(ColumnName.PATIENT_NAME);
				String surname = resultSet.getString(ColumnName.PATIENT_SURNAME);
				String status = resultSet.getString(ColumnName.PATIENT_STATUS_TYPE);
				long diagnosisId = resultSet.getLong(ColumnName.DIAGNOSIS_ID_FK);
				PatientData patient = new PatientData();
				patient.setId(id);
				patient.setName(name);
				patient.setSurname(surname);
				patient.setStatus(status);
				patient.setDiagnosisId(diagnosisId);
				patients.add(patient);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take patient data", ex);
		}
		return patients;
	}

	@Override
	public void changePatientStatus(long patientId, String status)
			throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_CHANGE_PATIENT_STATUS)) {
			statement.setByte(1, Byte.valueOf(status));
			statement.setLong(2, patientId);
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to change user status", ex);
		}
	}

	@Override
	public void changeDiagnosis(long patientId, long diagnosisId)
			throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_ESTABLISH_DIAGNOSIS)) {
			statement.setLong(1, diagnosisId);
			statement.setLong(2, patientId);
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to establish diagnosis", ex);
		}
	}

	@Override
	public PatientData takePatient(long patientId) throws DaoException {
		PatientData patient = new PatientData();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_SELECT_PATIENT)) {
			statement.setLong(1, patientId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString(ColumnName.PATIENT_NAME);
				String surname = resultSet.getString(ColumnName.PATIENT_SURNAME);
				String status = resultSet.getString(ColumnName.PATIENT_STATUS_TYPE);
				long diagnosisId = resultSet.getLong(ColumnName.DIAGNOSIS_ID_FK);
				String dateBirth = resultSet.getString(ColumnName.PATIENT_BIRTHDAY);
				String admissionDate = resultSet.getString(ColumnName.ADMISSION_DATE);
				String admissionDiagnosis = resultSet
						.getString(ColumnName.ADMISSION_DIAGNOSIS);
				String secondName = resultSet.getString(ColumnName.PATIENT_SECOND_NAME);
				String treatmentResult = resultSet
						.getString(ColumnName.TREATMENT_RESULT);
				String dischargeDate = resultSet.getString(ColumnName.DISCHARGE_DATE);
				patient.setId(patientId);
				patient.setName(name);
				patient.setSurname(surname);
				patient.setStatus(status);
				patient.setDiagnosisId(diagnosisId);
				patient.setDateBirth(dateBirth);
				patient.setAdmissionDate(admissionDate);
				patient.setAdmissionDiagnosis(admissionDiagnosis);
				patient.setSecondName(secondName);
				patient.setTreatmentResult(treatmentResult);
				patient.setDischargeDate(dischargeDate);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take patient data", ex);
		}
		return patient;
	}

	@Override
	public void dischargePatient(PatientData data) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_DISCHARGE_PATIENT)) {
			statement.setString(1, data.getTreatmentResult());
			statement.setLong(2, Long.valueOf(data.getDischargeDate()));
			statement.setLong(3, Long.valueOf(data.getId()));
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to discharge patient", ex);
		}
	}

}
