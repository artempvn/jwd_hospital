package by.artempvn.hospital.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.dao.PurposeDao;
import by.artempvn.hospital.model.dao.UserDao;
import by.artempvn.hospital.model.dao.UtilDao;
import by.artempvn.hospital.model.dao.impl.PurposeDaoImpl;
import by.artempvn.hospital.model.dao.impl.UserDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.DrugData;
import by.artempvn.hospital.model.entity.ProcedureData;
import by.artempvn.hospital.model.entity.PurposeData;
import by.artempvn.hospital.model.service.PurposeService;

/**
 * The Class PurposeServiceImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class PurposeServiceImpl implements PurposeService {
	private static final String ROLE_DOCTOR = "doctor";
	private static final int NOT_FOUND_ID = -1;
	private static final String KEY_DRUGS = "drugs";
	private static final String KEY_PROCEDURES = "procedures";
	private final Logger logger = LogManager.getLogger(PurposeServiceImpl.class);
	private static PurposeServiceImpl instance = new PurposeServiceImpl();

	private PurposeServiceImpl() {
	}

	public static PurposeServiceImpl getInstance() {
		return instance;
	}

	@Override
	public PurposeData changeAttendingDoctor(String attendingDoctorLogin,
			long patientId) throws ServiceException {
		PurposeData data = new PurposeData();
		try {
			UtilDao utilDao = UtilDaoImpl.getInstance();
			long purposeId = utilDao.takePurposeIdByPatientId(patientId);
			if (purposeId != NOT_FOUND_ID) {
				PurposeDao dao = PurposeDaoImpl.getInstance();
				long attendingDoctorId = Long
						.valueOf(utilDao.takeStaffIdByLogin(attendingDoctorLogin));
				UserDao userDao = UserDaoImpl.getInstance();
				String role = userDao.takeUserRole(attendingDoctorLogin);
				PurposeDao purposeDao = PurposeDaoImpl.getInstance();
				if (!purposeDao.isAnyOperationInPurpose(purposeId)
						|| role.equals(ROLE_DOCTOR)) {
					dao.changeAttendingDoctor(String.valueOf(purposeId),
							attendingDoctorId);
					data.setSucceed(true);
					logger.log(Level.INFO,
							"Doctor was added to purpose, id: " + purposeId);
				} else {
					logger.log(Level.WARN, "Nurse could't be selected for operation");
				}
			} else {
				data.setDiagnosisEstablished(false);
				logger.log(Level.WARN,
						"Couldn't change attending doctor without diagnosis");
			}
		} catch (DaoException ex) {
			throw new ServiceException("Failed to change attending doctor", ex);
		}
		return data;
	}

	@Override
	public void addDrugPurpose(long purposeId, long drugId)
			throws ServiceException {
		try {
			PurposeDao dao = PurposeDaoImpl.getInstance();
			dao.addDrugPurpose(purposeId, drugId);
			logger.log(Level.INFO,
					"Drug " + drugId + " was added to purpose " + purposeId);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to add drug to purpose", ex);
		}
	}

	@Override
	public void addProcedurePurpose(long purposeId, long procedureId)
			throws ServiceException {
		try {
			PurposeDao dao = PurposeDaoImpl.getInstance();
			dao.addProcedurePurpose(purposeId, procedureId);
			logger.log(Level.INFO,
					"Procedure " + procedureId + " was added to purpose " + purposeId);
		} catch (DaoException ex) {
			ex.printStackTrace();
			throw new ServiceException("Failed to add procedure to purpose", ex);
		}
	}

	@Override
	public long takeLastPurposeDate(long purposeId) throws ServiceException {
		long lastPurposeDate = 0;
		PurposeDao purposeDao = PurposeDaoImpl.getInstance();
		try {
			List<ProcedureData> procedures = purposeDao.takeProcedures(purposeId);
			for (ProcedureData procedureData : procedures) {
				if (Long.parseLong(procedureData.getDateEnd()) > lastPurposeDate) {
					lastPurposeDate = Long.parseLong(procedureData.getDateEnd());
				}
			}
			logger.log(Level.INFO, "Last purpose date is " + lastPurposeDate);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take last purpose date", ex);
		}
		return lastPurposeDate;
	}

	@Override
	public Map<String, Object> takePurpose(long patientId)
			throws ServiceException {
		Map<String, Object> purposeData = new HashMap<>();
		try {
			UtilDao utilDao = UtilDaoImpl.getInstance();
			long purposeId = utilDao.takePurposeIdByPatientId(patientId);
			if (purposeId != NOT_FOUND_ID) {
				PurposeDao purposeDao = PurposeDaoImpl.getInstance();
				List<DrugData> listDrugs = purposeDao.takeDrugs(purposeId);
				List<ProcedureData> listProcedures = purposeDao.takeProcedures(purposeId);
				purposeData.put(KEY_DRUGS, listDrugs);
				purposeData.put(KEY_PROCEDURES, listProcedures);
			} else {
				logger.log(Level.WARN, "Diagnosis hasn't been established");
			}
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take purpose data", ex);
		}
		return purposeData;
	}

	@Override
	public Map<String, Object> takePurposes(String userLogin)
			throws ServiceException {
		Map<String, Object> purposeData = new HashMap<>();
		try {
			UtilDao utilDao = UtilDaoImpl.getInstance();
			long userId = Long.valueOf(utilDao.takeStaffIdByLogin(userLogin));
			PurposeDao purposeDao = PurposeDaoImpl.getInstance();
			List<Long> purposes = purposeDao.takePurposeIdByAttendingDoctorId(userId);
			List<DrugData> drugs = new ArrayList<>();
			List<ProcedureData> procedures = new ArrayList<>();
			for (Long purposeId : purposes) {
				long patientId = utilDao.takePatientIdByPurposeId(purposeId);
				List<DrugData> drugList = purposeDao.takeDrugs(purposeId);
				drugList.stream().forEach(drug -> drug.setPatientId(patientId));
				drugs.addAll(drugList);
				List<ProcedureData> procedureList = purposeDao
						.takeProcedures(purposeId);
				procedureList.stream()
						.forEach(procedure -> procedure.setPatientId(patientId));
				procedures.addAll(procedureList);
			}
			purposeData.put(KEY_DRUGS, drugs);
			purposeData.put(KEY_PROCEDURES, procedures);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take purposes data", ex);
		}
		return purposeData;
	}

	@Override
	public boolean deleteDrugPurpose(long drugId) throws ServiceException {
		boolean isDeleted;
		try {
			PurposeDao dao = PurposeDaoImpl.getInstance();
			isDeleted = dao.deleteDrugPurpose(drugId);
			logger.log(Level.INFO, "Drug " + drugId + " was deleted: " + isDeleted);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to delete drug", ex);
		}
		return isDeleted;
	}

	@Override
	public boolean deleteProcedurePurpose(long procedureId)
			throws ServiceException {
		boolean isDeleted;
		try {
			PurposeDao dao = PurposeDaoImpl.getInstance();
			isDeleted = dao.deleteProcedurePurpose(procedureId);
			logger.log(Level.INFO,
					"Procedure " + procedureId + " was deleted: " + isDeleted);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to delete procedure", ex);
		}
		return isDeleted;
	}

}
