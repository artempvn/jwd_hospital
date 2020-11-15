package by.artempvn.hospital.model.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.dao.MedicalStaffDao;
import by.artempvn.hospital.model.dao.PatientDao;
import by.artempvn.hospital.model.dao.PurposeDao;
import by.artempvn.hospital.model.dao.UtilDao;
import by.artempvn.hospital.model.dao.impl.MedicalStaffDaoImpl;
import by.artempvn.hospital.model.dao.impl.PatientDaoImpl;
import by.artempvn.hospital.model.dao.impl.PurposeDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.PatientService;
import by.artempvn.hospital.model.service.PurposeService;
import by.artempvn.hospital.util.TimeConverter;
import by.artempvn.hospital.validator.DataValidator;

/**
 * The Class PatientServiceImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class PatientServiceImpl implements PatientService {
	private static final int MAX_LENGTH_TEXT_AREA = 1000;
	private static final String SCRIPT_REGEX = "</?script>";
	private static final String EMPTY_STRING = "";
	private static final String SPACE_DELIMETER = " ";
	private static final Object STATUS_ON_MEDICATION = "on_medication";
	private final Logger logger = LogManager.getLogger(PatientServiceImpl.class);
	private static PatientServiceImpl instance = new PatientServiceImpl();

	private PatientServiceImpl() {
	}

	public static PatientServiceImpl getInstance() {
		return instance;
	}

	@Override
	public PatientData addPatient(PatientData data) throws ServiceException {
		PatientData checkedData = checkPatientData(data);
		if (checkedData.isCorrectData()) {
			try {
				PatientDao dao = PatientDaoImpl.getInstance();
				UtilDao utilDao = UtilDaoImpl.getInstance();
				checkedData.setDateBirth(
						TimeConverter.convertFromIsoToSecondsDate(data.getDateBirth()));
				checkedData.setAdmissionDate(
						TimeConverter.convertFromIsoToSecondsDate(data.getAdmissionDate()));
				checkedData.setStatus(utilDao.takePatientStatusId(data.getStatus()));
				dao.addPatient(checkedData);
				logger.log(Level.INFO, "Patient '" + checkedData.getName()
						+ SPACE_DELIMETER + checkedData.getSurname() + "' was added");
			} catch (DaoException ex) {
				throw new ServiceException("Failed to add patient", ex);
			}
		}
		return checkedData;
	}

	private PatientData checkPatientData(PatientData data) {
		PatientData checkedData = new PatientData();
		checkedData.setCorrectData(true);
		if (DataValidator.isCorrectName(data.getName())) {
			checkedData.setName(data.getName());
		} else {
			logger.log(Level.WARN, "Incorrect name '" + data.getName() + "'");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectName(data.getSurname())) {
			checkedData.setSurname(data.getSurname());
		} else {
			logger.log(Level.WARN, "Incorrect surname '" + data.getSurname() + "'");
			checkedData.setCorrectData(false);
		}
		if ((data.getSecondName().length() > 0
				&& DataValidator.isCorrectName(data.getSecondName()))
				|| (data.getSecondName().length() == 0)) {
			checkedData.setSecondName(data.getSecondName());
		} else {
			logger.log(Level.WARN,
					"Incorrect second name '" + data.getSecondName() + "'");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectDate(data.getDateBirth())) {
			checkedData.setDateBirth(data.getDateBirth());
		} else {
			logger.log(Level.WARN, "Incorrect date'" + data.getDateBirth() + "'");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectDate(data.getAdmissionDate())) {
			checkedData.setAdmissionDate(data.getAdmissionDate());
		} else {
			logger.log(Level.WARN, "Incorrect date'" + data.getAdmissionDate() + "'");
			checkedData.setCorrectData(false);
		}
		if (data.getAdmissionDiagnosis().length() <= MAX_LENGTH_TEXT_AREA) {
			checkedData.setAdmissionDiagnosis(
					data.getAdmissionDiagnosis().replaceAll(SCRIPT_REGEX, EMPTY_STRING));
		} else {
			logger.log(Level.WARN, "Diagnosis couldn't be more then 1000 symbols");
			checkedData.setCorrectData(false);
		}
		return checkedData;
	}

	@Override
	public List<PatientData> takePatients() throws ServiceException {
		PatientDao dao = PatientDaoImpl.getInstance();
		List<PatientData> patients;
		try {
			patients = dao.takePatients();
			UtilDao utilDao = UtilDaoImpl.getInstance();
			PurposeDao purposeDao = PurposeDaoImpl.getInstance();
			MedicalStaffDao medicalStaffDao = MedicalStaffDaoImpl.getInstance();
			patients.stream().forEach(patient -> {
				if (patient.getDiagnosisId() != 0) {
					try {
						long purposeId = utilDao.takePurposeIdByPatientId(patient.getId());
						boolean isAnyPurposes = (purposeDao.takeDrugs(purposeId).size() > 0
								|| purposeDao.takeProcedures(purposeId).size() > 0);
						patient.setAnyPurpose(isAnyPurposes);
						long attendingDoctorId = purposeDao
								.takeAttendingDoctorId(purposeId);
						if (attendingDoctorId > 0) {
							UserData doctor = medicalStaffDao
									.takeMedicalStaffById(attendingDoctorId);
							patient.setAttendingDoctor(doctor);
						}
					} catch (DaoException ex) {
						logger.log(Level.ERROR,
								"Failed to check patient for purposes, id: " + patient.getId());
					}
				}
			});
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take patients", ex);
		}
		return patients;
	}

	@Override
	public PatientData dischargePatient(PatientData data)
			throws ServiceException {
		PatientData checkedData = checkDischargeData(data);
		if (checkedData.isCorrectData()) {
			UtilDao utilDao = UtilDaoImpl.getInstance();
			PatientDao patientDao = PatientDaoImpl.getInstance();
			try {
				PatientData patient = patientDao.takePatient(data.getId());
				if (patient.getStatus().equals(STATUS_ON_MEDICATION)) {
					long purposeId = utilDao.takePurposeIdByPatientId(data.getId());
					PurposeService service = PurposeServiceImpl.getInstance();
					long lastPurposeDate = service.takeLastPurposeDate(purposeId);
					long dateNow = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
					if (dateNow >= lastPurposeDate) {
						checkedData.setDischargeDate(TimeConverter
								.convertFromIsoToSecondsDate(data.getDischargeDate()));
						checkedData.setId(data.getId());
						patientDao.dischargePatient(checkedData);
						checkedData.setDischarged(true);
						logger.log(Level.INFO,
								"Patient was discharged, id: " + data.getId());
					} else {
						logger.log(Level.WARN,
								"Patient couldn't be discharged because there is some purpose left, id: "
										+ data.getId());
					}
				} else {
					logger.log(Level.WARN,
							"Patient couldn't be discharged due to his status, id: "
									+ data.getId());
				}
			} catch (DaoException ex) {
				throw new ServiceException("Failed to discharge patient", ex);
			}
		}
		return checkedData;
	}

	private PatientData checkDischargeData(PatientData data) {
		PatientData checkedData = new PatientData();
		checkedData.setCorrectData(true);
		if (DataValidator.isTextFieldCorrect(data.getTreatmentResult())) {
			checkedData.setTreatmentResult(
					data.getTreatmentResult().replaceAll(SCRIPT_REGEX, EMPTY_STRING));
		} else {
			logger.log(Level.WARN,
					"Result couldn't be empty or be more then 1000 symbols");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectDate(data.getDischargeDate())) {
			checkedData.setDischargeDate(data.getDischargeDate());
		} else {
			logger.log(Level.WARN, "Incorrect date'" + data.getDischargeDate() + "'");
			checkedData.setCorrectData(false);
		}
		return checkedData;
	}

	@Override
	public PatientData takePatient(long patientId) throws ServiceException {
		PatientDao dao = PatientDaoImpl.getInstance();
		PatientData patient;
		try {
			patient = dao.takePatient(patientId);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take patient", ex);
		}
		return patient;
	}

}
