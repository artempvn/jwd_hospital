package by.artempvn.hospital.model.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.dao.DiagnosisDao;
import by.artempvn.hospital.model.dao.MedicalStaffDao;
import by.artempvn.hospital.model.dao.PatientDao;
import by.artempvn.hospital.model.dao.PurposeDao;
import by.artempvn.hospital.model.dao.UtilDao;
import by.artempvn.hospital.model.dao.impl.DiagnosisDaoImpl;
import by.artempvn.hospital.model.dao.impl.MedicalStaffDaoImpl;
import by.artempvn.hospital.model.dao.impl.PatientDaoImpl;
import by.artempvn.hospital.model.dao.impl.PurposeDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.DiagnosisData;
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.DiagnosisService;
import by.artempvn.hospital.util.TimeConverter;
import by.artempvn.hospital.validator.DataValidator;

/**
 * The Class DiagnosisServiceImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DiagnosisServiceImpl implements DiagnosisService {
	private static final String KEY_ESTABLISHED_DOCTOR = "established_doctor";
	private static final String KEY_DIAGNOSIS = "diagnosis";
	private static final String SCRIPT_REGEX = "</?script>";
	private static final String EMPTY_STRING = "";
	private static final String STATUS_ON_MEDICATION = "on_medication";
	private final Logger logger = LogManager
			.getLogger(DiagnosisServiceImpl.class);
	private static DiagnosisServiceImpl instance = new DiagnosisServiceImpl();

	private DiagnosisServiceImpl() {
	}

	public static DiagnosisServiceImpl getInstance() {
		return instance;
	}

	@Override
	public DiagnosisData addDiagnosis(DiagnosisData data)
			throws ServiceException {
		DiagnosisData checkedData;
		try {
			checkedData = checkDiagnosisData(data);
			if (checkedData.isCorrectData()) {
				DiagnosisDao dao = DiagnosisDaoImpl.getInstance();
				UtilDao utilDao = UtilDaoImpl.getInstance();
				checkedData.setEstablishedDoctor(
						utilDao.takeStaffIdByLogin(data.getEstablishedDoctor()));
				checkedData
						.setDate(TimeConverter.convertFromIsoToSecondsDate(data.getDate()));
				PurposeDao purposeDao = PurposeDaoImpl.getInstance();
				long purposeId = purposeDao.addPurpose();
				checkedData.setPurposeId(purposeId);
				checkedData.setDiagnosisId(dao.addDiagnosis(checkedData));
				PatientDao patientDao = PatientDaoImpl.getInstance();
				patientDao.changeDiagnosis(checkedData.getPatientId(),
						checkedData.getDiagnosisId());
				patientDao.changePatientStatus(checkedData.getPatientId(),
						utilDao.takePatientStatusId(STATUS_ON_MEDICATION));
				logger.log(Level.INFO,
						"Diagnosis was added, id: " + checkedData.getDiagnosisId());
			}
		} catch (DaoException ex) {
			throw new ServiceException("Failed to add diagnosis", ex);
		}
		return checkedData;
	}

	private DiagnosisData checkDiagnosisData(DiagnosisData data) {
		DiagnosisData checkedData = new DiagnosisData();
		checkedData.setCorrectData(true);
		if (DataValidator.isTextFieldCorrect(data.getName())) {
			checkedData
					.setName(data.getName().replaceAll(SCRIPT_REGEX, EMPTY_STRING));
		} else {
			logger.log(Level.WARN,
					"Diagnosis couldn't be empty or be more then 1000 symbols");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectDate(data.getDate())) {
			checkedData.setDate(data.getDate());
		} else {
			logger.log(Level.WARN, "Incorrect date'" + data.getDate() + "'");
			checkedData.setCorrectData(false);
		}
		checkedData.setEstablishedDoctor(data.getEstablishedDoctor());
		checkedData.setPatientId(data.getPatientId());
		checkedData.setPurposeId(data.getPurposeId());
		return checkedData;
	}

	@Override
	public Map<String, Object> takeDiagnosisDoctorData(long patientId)
			throws ServiceException {
		Map<String, Object> diagnosisData = new HashMap<>();
		try {
			PatientDao patientDao = PatientDaoImpl.getInstance();
			PatientData patient = patientDao.takePatient(patientId);
			long diagnosisId = patient.getDiagnosisId();
			DiagnosisDao diagnosisDao = DiagnosisDaoImpl.getInstance();
			DiagnosisData diagnosis = diagnosisDao.takeDiagnosis(diagnosisId);
			if (diagnosis.getEstablishedDoctor() != null) {
				long doctorEstablishedDiagnosisId = Long
						.valueOf(diagnosis.getEstablishedDoctor());
				MedicalStaffDao medicalStaffDao = MedicalStaffDaoImpl.getInstance();
				UserData doctorEstablishedDiagnosis = medicalStaffDao
						.takeMedicalStaffById(doctorEstablishedDiagnosisId);
				diagnosisData.put(KEY_DIAGNOSIS, diagnosis);
				diagnosisData.put(KEY_ESTABLISHED_DOCTOR, doctorEstablishedDiagnosis);
			} else {
				logger.log(Level.WARN, "Diagnosis hasn't been established");
			}
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take diagnosis&doctor data", ex);
		}
		return diagnosisData;
	}

	@Override
	public boolean updateDiagnosis(DiagnosisData diagnosisData)
			throws ServiceException {
		boolean isCorrectData;
		if (DataValidator.isTextFieldCorrect(diagnosisData.getName())) {
			diagnosisData.setName(
					diagnosisData.getName().replaceAll(SCRIPT_REGEX, EMPTY_STRING));
			try {
				DiagnosisDao dao = DiagnosisDaoImpl.getInstance();
				dao.updateDiagnosis(diagnosisData);
				isCorrectData = true;
				logger.log(Level.INFO, "Diagnosis was updated, patient ID: "
						+ diagnosisData.getPatientId());
			} catch (DaoException ex) {
				throw new ServiceException("Failed to update diagnosis", ex);
			}
		} else {
			isCorrectData = false;
			logger.log(Level.WARN,
					"Diagnosis couldn't be empty or be more then 1000 symbols");
		}
		return isCorrectData;
	}

}
