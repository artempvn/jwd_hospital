package by.artempvn.hospital.model.service.impl;

import java.util.List;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.dao.UtilDao;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;

/**
 * The Class UtilServiceImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class UtilServiceImpl {
	private static UtilServiceImpl instance = new UtilServiceImpl();

	private UtilServiceImpl() {
	}

	public static UtilServiceImpl getInstance() {
		return instance;
	}

	public List<String> takeStaffRoles() throws ServiceException {
		UtilDao dao = UtilDaoImpl.getInstance();
		List<String> roles;
		try {
			roles = dao.takeStaffRoles();
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take staff roles", ex);
		}
		return roles;
	}

	public List<String> takeStaffSpecialities() throws ServiceException {
		UtilDao dao = UtilDaoImpl.getInstance();
		List<String> specialities;
		try {
			specialities = dao.takeStaffSpecialities();
		} catch (DaoException ex) {
			throw new ServiceException("Failed to take staff specialities", ex);
		}
		return specialities;
	}

}
