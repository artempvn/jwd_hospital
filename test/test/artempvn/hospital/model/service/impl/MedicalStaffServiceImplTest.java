package test.artempvn.hospital.model.service.impl;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.dao.impl.MedicalStaffDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.impl.MedicalStaffServiceImpl;

/**
 * The Class DataValidatorTest.
 * 
 * @author Artem Piven
 * @version 1.0
 */
@PowerMockIgnore({ "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*",
		"org.w3c.*", "com.sun.org.apache.xalan.*", "javax.management.*" })
@PrepareForTest({ UtilDaoImpl.class, MedicalStaffDaoImpl.class })
public class MedicalStaffServiceImplTest {
	MedicalStaffServiceImpl service;
	UtilDaoImpl utilDao;
	MedicalStaffDaoImpl medicalStaffDao;

	@ObjectFactory
	public IObjectFactory setObjectFactory() {
		return new PowerMockObjectFactory();
	}

	@BeforeMethod
	public void setUp() {
		PowerMock.mockStatic(UtilDaoImpl.class);
		utilDao = EasyMock.createNiceMock(UtilDaoImpl.class);
		EasyMock.expect(UtilDaoImpl.getInstance()).andReturn(utilDao);
		PowerMock.mockStatic(MedicalStaffDaoImpl.class);
		medicalStaffDao = EasyMock.createNiceMock(MedicalStaffDaoImpl.class);
		EasyMock.expect(MedicalStaffDaoImpl.getInstance())
				.andReturn(medicalStaffDao);
		service = MedicalStaffServiceImpl.getInstance();
	}

	@Test
	public void takeMedicalStaffTestPositive()
			throws ServiceException, DaoException {
		List<UserData> list = new ArrayList<>();
		EasyMock.expect(medicalStaffDao.takeMedicalStaff(EasyMock.anyBoolean()))
				.andReturn(list);
		PowerMock.replayAll(medicalStaffDao);
		List<UserData> actual = service.takeMedicalStaff(true);
		List<UserData> expected = new ArrayList<>();
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeMedicalStaffTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(medicalStaffDao.takeMedicalStaff(EasyMock.anyBoolean()))
				.andThrow(new DaoException("Failed to take medical staff"));
		PowerMock.replayAll(medicalStaffDao);
		service.takeMedicalStaff(true);
	}

	@Test
	public void takeMedicalStaffByLoginTestPositive()
			throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffIdByLogin(EasyMock.anyObject()))
				.andReturn("1");
		UserData user = new UserData();
		user.setLogin("admin");
		EasyMock.expect(medicalStaffDao.takeMedicalStaffById(1L)).andReturn(user);
		PowerMock.replayAll(medicalStaffDao, utilDao);
		UserData actual = service.takeMedicalStaffByLogin("admin");
		UserData expected = new UserData();
		expected.setLogin("admin");
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeMedicalStaffByLoginTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffIdByLogin(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to take medical staff"));
		PowerMock.replayAll(utilDao);
		service.takeMedicalStaffByLogin("admin");
	}

}
