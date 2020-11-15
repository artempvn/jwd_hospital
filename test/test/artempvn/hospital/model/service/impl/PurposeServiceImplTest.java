package test.artempvn.hospital.model.service.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import by.artempvn.hospital.model.entity.DiagnosisData;
import by.artempvn.hospital.model.entity.ProcedureData;
import by.artempvn.hospital.model.entity.PurposeData;
import by.artempvn.hospital.model.service.impl.PurposeServiceImpl;

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
public class PurposeServiceImplTest {
	PurposeServiceImpl service;
	DiagnosisData inputData;
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
		PowerMock.mockStatic(UserDaoImpl.class);
		userDao = EasyMock.createNiceMock(UserDaoImpl.class);
		EasyMock.expect(UserDaoImpl.getInstance()).andReturn(userDao);
		PowerMock.mockStatic(UtilDaoImpl.class);
		utilDao = EasyMock.createNiceMock(UtilDaoImpl.class);
		EasyMock.expect(UtilDaoImpl.getInstance()).andReturn(utilDao);
		PowerMock.mockStatic(PurposeDaoImpl.class);
		purposeDao = EasyMock.createNiceMock(PurposeDaoImpl.class);
		EasyMock.expect(PurposeDaoImpl.getInstance()).andReturn(purposeDao)
				.anyTimes();
		PowerMock.mockStatic(PatientDaoImpl.class);
		patientDao = EasyMock.createNiceMock(PatientDaoImpl.class);
		EasyMock.expect(PatientDaoImpl.getInstance()).andReturn(patientDao);
		PowerMock.mockStatic(MedicalStaffDaoImpl.class);
		medicalStaffDao = EasyMock.createNiceMock(MedicalStaffDaoImpl.class);
		EasyMock.expect(MedicalStaffDaoImpl.getInstance())
				.andReturn(medicalStaffDao);
		service = PurposeServiceImpl.getInstance();
		inputData = new DiagnosisData();
		inputData.setDate("1970-01-01");
		inputData.setName("correct text field");
	}

	@Test
	public void changeAttendingDoctorTestPositive()
			throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takePurposeIdByPatientId(EasyMock.anyLong()))
				.andReturn(1L);
		EasyMock.expect(utilDao.takeStaffIdByLogin(EasyMock.anyObject()))
				.andReturn("1");
		EasyMock.expect(purposeDao.isAnyOperationInPurpose(EasyMock.anyLong()))
				.andReturn(false);
		PowerMock.replayAll(utilDao, purposeDao);
		PurposeData actual = service.changeAttendingDoctor("login", 1L);
		PurposeData expected = new PurposeData();
		expected.setSucceed(true);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void changeAttendingDoctorTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takePurposeIdByPatientId(EasyMock.anyLong()))
				.andThrow(new DaoException("Failed to change attending doctor"));
		PowerMock.replayAll(utilDao);
		service.changeAttendingDoctor("login", 1L);
	}

	@Test
	public void addDrugPurposeTestPositive()
			throws ServiceException, DaoException {
		PowerMock.replayAll(purposeDao);
		service.addDrugPurpose(1, 1);
		assertTrue(true);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void addDrugPurposeTestNegative()
			throws ServiceException, DaoException {
		purposeDao.addDrugPurpose(1, 1);
		EasyMock.expectLastCall()
				.andThrow(new DaoException("Failed to add drug to purpose"));
		PowerMock.replayAll(purposeDao);
		service.addDrugPurpose(1, 1);
	}

	@Test
	public void addProcedurePurposeTestPositive()
			throws ServiceException, DaoException {
		PowerMock.replayAll(purposeDao);
		service.addProcedurePurpose(1, 1);
		assertTrue(true);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void addProcedurePurposeTestNegative()
			throws ServiceException, DaoException {
		purposeDao.addProcedurePurpose(1, 1);
		EasyMock.expectLastCall()
				.andThrow(new DaoException("Failed to add procedure to purpose"));
		PowerMock.replayAll(purposeDao);
		service.addProcedurePurpose(1, 1);
	}

	@Test
	public void takeLastPurposeDateTestPositive()
			throws ServiceException, DaoException {
		ProcedureData procedure = new ProcedureData();
		procedure.setDateEnd("1000");
		ProcedureData procedure1 = new ProcedureData();
		procedure1.setDateEnd("1200");
		List<ProcedureData> procedures = Arrays.asList(procedure, procedure1);
		EasyMock.expect(purposeDao.takeProcedures(EasyMock.anyLong()))
				.andReturn(procedures);
		PowerMock.replayAll(purposeDao);
		long actual = service.takeLastPurposeDate(1L);
		long expected = 1200;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeLastPurposeDateTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(purposeDao.takeProcedures(EasyMock.anyLong()))
				.andThrow(new DaoException("Failed to take last purpose date"));
		PowerMock.replayAll(purposeDao);
		service.takeLastPurposeDate(1L);
	}

	@Test
	public void takePurposeTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takePurposeIdByPatientId(EasyMock.anyLong()))
				.andReturn(-1L);
		PowerMock.replayAll(utilDao);
		Map<String, Object> actual = service.takePurpose(1L);
		Map<String, Object> expected = new HashMap<>();
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takePurposeTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takePurposeIdByPatientId(EasyMock.anyLong()))
				.andThrow(new DaoException("Failed to take purpose data"));
		PowerMock.replayAll(utilDao);
		service.takePurpose(1L);
	}

	@Test
	public void takePurposesTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffIdByLogin(EasyMock.anyObject()))
				.andReturn("1");
		EasyMock
				.expect(purposeDao.takePurposeIdByAttendingDoctorId(EasyMock.anyLong()))
				.andReturn(Collections.emptyList());
		PowerMock.replayAll(utilDao, purposeDao);
		Map<String, Object> actual = service.takePurposes("login");
		Map<String, Object> expected = new HashMap<>();
		expected.put("drugs", Collections.emptyList());
		expected.put("procedures", Collections.emptyList());
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takePurposesTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffIdByLogin(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to take purposes data"));
		PowerMock.replayAll(utilDao);
		service.takePurposes("login");
	}

	@Test
	public void deleteDrugPurposeTestPositive()
			throws ServiceException, DaoException {
		EasyMock.expect(purposeDao.deleteDrugPurpose(EasyMock.anyLong()))
				.andReturn(true);
		PowerMock.replayAll(purposeDao);
		boolean actual = service.deleteDrugPurpose(1L);
		boolean expected = true;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void deleteDrugPurposeTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(purposeDao.deleteDrugPurpose(EasyMock.anyLong()))
				.andThrow(new DaoException("Failed to delete drug"));
		PowerMock.replayAll(purposeDao);
		service.deleteDrugPurpose(1L);
	}

	@Test
	public void deleteProcedurePurposeTestPositive()
			throws ServiceException, DaoException {
		EasyMock.expect(purposeDao.deleteProcedurePurpose(EasyMock.anyLong()))
				.andReturn(true);
		PowerMock.replayAll(purposeDao);
		boolean actual = service.deleteProcedurePurpose(1L);
		boolean expected = true;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void deleteProcedurePurposeTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(purposeDao.deleteProcedurePurpose(EasyMock.anyLong()))
				.andThrow(new DaoException("Failed to delete procedure"));
		PowerMock.replayAll(purposeDao);
		service.deleteProcedurePurpose(1L);
	}

}
