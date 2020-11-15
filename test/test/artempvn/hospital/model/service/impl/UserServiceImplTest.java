package test.artempvn.hospital.model.service.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.Arrays;
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
import by.artempvn.hospital.model.dao.impl.DiagnosisDaoImpl;
import by.artempvn.hospital.model.dao.impl.MedicalStaffDaoImpl;
import by.artempvn.hospital.model.dao.impl.PatientDaoImpl;
import by.artempvn.hospital.model.dao.impl.PurposeDaoImpl;
import by.artempvn.hospital.model.dao.impl.UserDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.impl.UserServiceImpl;

/**
 * The Class DataValidatorTest.
 * 
 * @author Artem Piven
 * @version 1.0
 */
@PowerMockIgnore({ "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*",
		"org.w3c.*", "com.sun.org.apache.xalan.*", "javax.management.*" })
@PrepareForTest({ DiagnosisDaoImpl.class, UtilDaoImpl.class,
		PurposeDaoImpl.class, PatientDaoImpl.class, MedicalStaffDaoImpl.class,
		UserDaoImpl.class })
public class UserServiceImplTest {
	UserServiceImpl service;
	UserData userData;
	DiagnosisDaoImpl diagnosisDao;
	UtilDaoImpl utilDao;
	PurposeDaoImpl purposeDao;
	PatientDaoImpl patientDao;
	MedicalStaffDaoImpl medicalStaffDao;
	UserDaoImpl userDao;

	@ObjectFactory
	public IObjectFactory setObjectFactory() {
		return new PowerMockObjectFactory();
	}

	@BeforeMethod
	public void setUp() {
		PowerMock.mockStatic(DiagnosisDaoImpl.class);
		diagnosisDao = EasyMock.createNiceMock(DiagnosisDaoImpl.class);
		EasyMock.expect(DiagnosisDaoImpl.getInstance()).andReturn(diagnosisDao);
		PowerMock.mockStatic(UtilDaoImpl.class);
		utilDao = EasyMock.createNiceMock(UtilDaoImpl.class);
		EasyMock.expect(UtilDaoImpl.getInstance()).andReturn(utilDao).anyTimes();
		PowerMock.mockStatic(PurposeDaoImpl.class);
		purposeDao = EasyMock.createNiceMock(PurposeDaoImpl.class);
		EasyMock.expect(PurposeDaoImpl.getInstance()).andReturn(purposeDao);
		PowerMock.mockStatic(PatientDaoImpl.class);
		patientDao = EasyMock.createNiceMock(PatientDaoImpl.class);
		EasyMock.expect(PatientDaoImpl.getInstance()).andReturn(patientDao);
		PowerMock.mockStatic(MedicalStaffDaoImpl.class);
		medicalStaffDao = EasyMock.createNiceMock(MedicalStaffDaoImpl.class);
		EasyMock.expect(MedicalStaffDaoImpl.getInstance())
				.andReturn(medicalStaffDao);
		PowerMock.mockStatic(UserDaoImpl.class);
		userDao = EasyMock.createNiceMock(UserDaoImpl.class);
		EasyMock.expect(UserDaoImpl.getInstance()).andReturn(userDao);
		service = UserServiceImpl.getInstance();
		userData = new UserData();
		userData.setLogin("nurse");
		userData.setEmail("nurse@mail.ru");
		userData.setName("test");
		userData.setSurname("test");
		userData.setSecondName("test");
		userData.setPassword("pass");
		userData.setRole("nurse");
		userData.setSpeciality("nurse");
	}

	@Test
	public void checkPasswordTestPositive()
			throws ServiceException, DaoException {
		EasyMock.expect(userDao.takeUserPassword(EasyMock.anyObject()))
				.andReturn("160141163163");
		PowerMock.replayAll(userDao);
		boolean actual = service.checkPassword("login", "pass");
		boolean expected = true;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void checkPasswordTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(userDao.takeUserPassword(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to take password"));
		PowerMock.replayAll(userDao);
		service.checkPassword("login", "codedPass");
	}

	@Test
	public void takeRoleTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(userDao.takeUserRole(EasyMock.anyObject()))
				.andReturn("admin");
		PowerMock.replayAll(userDao);
		String actual = service.takeRole("login");
		String expected = "admin";
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeRoleTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(userDao.takeUserRole(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to take role"));
		PowerMock.replayAll(userDao);
		service.takeRole("login");
	}

	@Test
	public void addUserTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(userDao.addUser(EasyMock.anyObject())).andReturn(false);
		EasyMock.expect(utilDao.takeStaffRoles()).andReturn(Arrays.asList("nurse"));
		EasyMock.expect(utilDao.takeStaffSpecialities())
				.andReturn(Arrays.asList("nurse"));
		PowerMock.replayAll(userDao, utilDao);
		UserData actual = service.addUser(userData);
		UserData expected = new UserData();
		expected.setLogin("nurse");
		expected.setEmail("nurse@mail.ru");
		expected.setName("test");
		expected.setSurname("test");
		expected.setSecondName("test");
		expected.setPassword("pass");
		expected.setRole("nurse");
		expected.setSpeciality("nurse");
		expected.setCorrectData(true);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void addUserTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffRoles())
				.andThrow(new DaoException("Failed to add user"));
		PowerMock.replayAll(utilDao);
		service.addUser(userData);
	}

	@Test
	public void activateUserTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(userDao.activateUser(EasyMock.anyObject())).andReturn(true);
		PowerMock.replayAll(userDao);
		boolean actual = service.activateUser("login");
		boolean expected = true;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void activateUserTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(userDao.activateUser(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to change user status"));
		PowerMock.replayAll(userDao);
		service.activateUser("login");
	}

	@Test
	public void takeUserStatusTestPositive()
			throws ServiceException, DaoException {
		EasyMock.expect(userDao.takeUserStatus(EasyMock.anyObject()))
				.andReturn("admin");
		PowerMock.replayAll(userDao);
		String actual = service.takeUserStatus("login");
		String expected = "admin";
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeUserStatusTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(userDao.takeUserStatus(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to take status"));
		PowerMock.replayAll(userDao);
		service.takeUserStatus("login");
	}

	@Test
	public void banUserTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffStatusId(EasyMock.anyObject()))
				.andReturn("1");
		PowerMock.replayAll(userDao, utilDao);
		service.banUser("login");
		assertTrue(true);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void banUserTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffStatusId(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to ban user"));
		PowerMock.replayAll(utilDao);
		service.banUser("login");
	}

	@Test
	public void unbanUserTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffStatusId(EasyMock.anyObject()))
				.andReturn("1");
		PowerMock.replayAll(userDao, utilDao);
		service.unbanUser("login");
		assertTrue(true);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void unbanUserTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffStatusId(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to unban user"));
		PowerMock.replayAll(utilDao);
		service.unbanUser("login");
	}

}
