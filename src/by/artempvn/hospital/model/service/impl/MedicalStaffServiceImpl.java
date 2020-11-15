package by.artempvn.hospital.model.service.impl;

import java.util.List;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.dao.MedicalStaffDao;
import by.artempvn.hospital.model.dao.UtilDao;
import by.artempvn.hospital.model.dao.impl.MedicalStaffDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.MedicalStaffService;

/**
 * The Class MedicalStaffServiceImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class MedicalStaffServiceImpl implements MedicalStaffService {
	private static MedicalStaffServiceImpl instance = new MedicalStaffServiceImpl();

	private MedicalStaffServiceImpl() {
	}

	public static MedicalStaffServiceImpl getInstance() {
		return instance;
	}

	@Override
	public List<UserData> takeMedicalStaff(boolean takeOnlyActive)
			throws ServiceException {
		MedicalStaffDao dao = MedicalStaffDaoImpl.getInstance();
		List<UserData> staff;
		try {
			staff = dao.takeMedicalStaff(takeOnlyActive);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take medical staff", ex);
		}
		return staff;
	}

	@Override
	public UserData takeMedicalStaffByLogin(String login)
			throws ServiceException {
		MedicalStaffDao dao = MedicalStaffDaoImpl.getInstance();
		UtilDao utilDao = UtilDaoImpl.getInstance();
		UserData staff;
		try {
			long id = Long.parseLong(utilDao.takeStaffIdByLogin(login));
			staff = dao.takeMedicalStaffById(id);
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take medical staff", ex);
		}
		return staff;
	}

}
