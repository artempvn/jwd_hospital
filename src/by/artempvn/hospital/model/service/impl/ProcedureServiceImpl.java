package by.artempvn.hospital.model.service.impl;

import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.dao.ProcedureDao;
import by.artempvn.hospital.model.dao.PurposeDao;
import by.artempvn.hospital.model.dao.UserDao;
import by.artempvn.hospital.model.dao.UtilDao;
import by.artempvn.hospital.model.dao.impl.ProcedureDaoImpl;
import by.artempvn.hospital.model.dao.impl.PurposeDaoImpl;
import by.artempvn.hospital.model.dao.impl.UserDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.ProcedureData;
import by.artempvn.hospital.model.service.ProcedureService;
import by.artempvn.hospital.util.TimeConverter;
import by.artempvn.hospital.validator.DataValidator;

/**
 * The Class ProcedureServiceImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ProcedureServiceImpl implements ProcedureService {
	private static final int NOT_FOUND_ID = -1;
	private static final String EMPTY_STRING = "";
	private static final String SCRIPT_REGEX = "</?script>";
	private static final String ROLE_DOCTOR = "doctor";
	private static final String PROCEDURE_OPERATION_TYPE = "operation";
	private final Logger logger = LogManager
			.getLogger(ProcedureServiceImpl.class);
	private static ProcedureServiceImpl instance = new ProcedureServiceImpl();

	private ProcedureServiceImpl() {
	}

	public static ProcedureServiceImpl getInstance() {
		return instance;
	}

	@Override
	public ProcedureData addProcedure(ProcedureData data)
			throws ServiceException {
		ProcedureData checkedData;
		try {
			checkedData = checkProcedureData(data);
			if (checkedData.isCorrectData()) {
				UtilDao utilDao = UtilDaoImpl.getInstance();
				long purposeId = utilDao
						.takePurposeIdByPatientId(checkedData.getPatientId());
				if (purposeId != NOT_FOUND_ID) {
					ProcedureDao dao = ProcedureDaoImpl.getInstance();
					String procedureType = checkedData.getType();
					checkedData.setType(utilDao.takeProcedureTypeId(procedureType));
					checkedData.setDateStart(TimeConverter
							.convertFromIsoToSecondsDateTime(data.getDateStart()));
					checkedData.setDateEnd(
							TimeConverter.convertFromIsoToSecondsDateTime(data.getDateEnd()));
					checkedData.setProcedureId(dao.addProcedure(checkedData));
					PurposeDao purposeDao = PurposeDaoImpl.getInstance();
					purposeDao.addProcedurePurpose(purposeId,
							checkedData.getProcedureId());
					long attendingDoctorId = purposeDao.takeAttendingDoctorId(purposeId);
					if (attendingDoctorId != NOT_FOUND_ID) {
						UserDao userDao = UserDaoImpl.getInstance();
						String role = userDao.takeUserRole(attendingDoctorId);
						if (procedureType.equals(PROCEDURE_OPERATION_TYPE)
								&& (role != null && !role.equals(ROLE_DOCTOR))) {
							purposeDao.removeAttendingDoctor(purposeId);
							checkedData.setDoctorRemoved(true);
							logger.log(Level.INFO, "Nurse was removed from purpose");
						}
					}
					logger.log(Level.INFO,
							"Procedure was added, id: " + checkedData.getProcedureId());
				} else {
					checkedData.setDiagnosisEstablished(false);
					checkedData.setCorrectData(false);
					logger.log(Level.WARN, "Couldn't add procedure without diagnosis");
				}
			}
		} catch (DaoException ex) {
			throw new ServiceException("Failed to add procedure", ex);
		}
		return checkedData;
	}

	private ProcedureData checkProcedureData(ProcedureData data)
			throws DaoException {
		ProcedureData checkedData = new ProcedureData();
		checkedData.setCorrectData(true);
		if (DataValidator.isTextFieldCorrect(data.getName())) {
			checkedData
					.setName(data.getName().replaceAll(SCRIPT_REGEX, EMPTY_STRING));
		} else {
			logger.log(Level.WARN,
					"Procedure couldn't be empty or be more then 1000 symbols");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectDateTime(data.getDateStart())) {
			checkedData.setDateStart(data.getDateStart());
		} else {
			logger.log(Level.WARN, "Incorrect date'" + data.getDateStart() + "'");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isCorrectDateTime(data.getDateEnd())) {
			checkedData.setDateEnd(data.getDateEnd());
		} else {
			logger.log(Level.WARN, "Incorrect date'" + data.getDateEnd() + "'");
			checkedData.setCorrectData(false);
		}
		UtilDao dao = UtilDaoImpl.getInstance();
		List<String> types = dao.takeProcedureTypes();
		if (types.contains(data.getType())) {
			checkedData.setType(data.getType());
		} else {
			logger.log(Level.WARN,
					"Incorrect procedure type'" + data.getType() + "'");
			checkedData.setCorrectData(false);
		}
		checkedData.setPatientId(data.getPatientId());
		return checkedData;
	}

}
