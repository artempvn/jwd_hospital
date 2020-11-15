package by.artempvn.hospital.model.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.dao.DrugDao;
import by.artempvn.hospital.model.dao.PurposeDao;
import by.artempvn.hospital.model.dao.UtilDao;
import by.artempvn.hospital.model.dao.impl.DrugDaoImpl;
import by.artempvn.hospital.model.dao.impl.PurposeDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.DrugData;
import by.artempvn.hospital.model.service.DrugService;
import by.artempvn.hospital.validator.DataValidator;

/**
 * The Class DrugServiceImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DrugServiceImpl implements DrugService {
	private static final String SCRIPT_REGEX = "</?script>";
	private static final String EMPTY_STRING = "";
	private final Logger logger = LogManager.getLogger(DrugServiceImpl.class);
	private static DrugServiceImpl instance = new DrugServiceImpl();

	private DrugServiceImpl() {
	}

	public static DrugServiceImpl getInstance() {
		return instance;
	}

	@Override
	public DrugData addDrug(DrugData data) throws ServiceException {
		DrugData checkedData = checkDrugData(data);
		if (checkedData.isCorrectData()) {
			try {
				UtilDao utilDao = UtilDaoImpl.getInstance();
				long purposeId = utilDao
						.takePurposeIdByPatientId(checkedData.getPatientId());
				if (purposeId != -1) {
					DrugDao dao = DrugDaoImpl.getInstance();
					checkedData.setDrugId(dao.addDrug(checkedData));
					PurposeDao purposeDao = PurposeDaoImpl.getInstance();
					purposeDao.addDrugPurpose(purposeId, checkedData.getDrugId());
					logger.log(Level.INFO,
							"Drug was added, id: " + checkedData.getDrugId());
				} else {
					checkedData.setDiagnosisEstablished(false);
					checkedData.setCorrectData(false);
					logger.log(Level.WARN, "Couldn't add drug without diagnosis");
				}
			} catch (DaoException ex) {
				throw new ServiceException("Failed to add drug", ex);
			}
		}
		return checkedData;
	}

	private DrugData checkDrugData(DrugData data) {
		DrugData checkedData = new DrugData();
		checkedData.setCorrectData(true);
		if (DataValidator.isTextFieldCorrect(data.getName())) {
			checkedData
					.setName(data.getName().replaceAll(SCRIPT_REGEX, EMPTY_STRING));
		} else {
			logger.log(Level.WARN,
					"Drug couldn't be empty or be more then 1000 symbols");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isTextFieldCorrect(data.getAmount())) {
			checkedData
					.setAmount(data.getAmount().replaceAll(SCRIPT_REGEX, EMPTY_STRING));
		} else {
			logger.log(Level.WARN,
					"Amount couldn't be empty or be more then 1000 symbols");
			checkedData.setCorrectData(false);
		}
		if (DataValidator.isTextFieldCorrect(data.getAppointmentTime())) {
			checkedData.setAppointmentTime(
					data.getAppointmentTime().replaceAll(SCRIPT_REGEX, EMPTY_STRING));
		} else {
			logger.log(Level.WARN,
					"Appointment time couldn't be empty or be more then 1000 symbols");
			checkedData.setCorrectData(false);
		}
		checkedData.setPatientId(data.getPatientId());
		return checkedData;
	}

}
