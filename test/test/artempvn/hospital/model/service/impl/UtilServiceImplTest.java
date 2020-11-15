package test.artempvn.hospital.model.service.impl;

import static org.testng.Assert.assertEquals;
import java.util.Collections;
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
import by.artempvn.hospital.model.dao.impl.DiagnosisDaoImpl;
import by.artempvn.hospital.model.dao.impl.MedicalStaffDaoImpl;
import by.artempvn.hospital.model.dao.impl.PatientDaoImpl;
import by.artempvn.hospital.model.dao.impl.PurposeDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.DiagnosisData;
import by.artempvn.hospital.model.service.impl.UtilServiceImpl;

/**
 * The Class DataValidatorTest.
 * 
 * @author Artem Piven
 * @version 1.0
 */
@PowerMockIgnore({ "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*",
		"org.w3c.*", "com.sun.org.apache.xalan.*", "javax.management.*" })
@PrepareForTest({ DiagnosisDaoImpl.class, UtilDaoImpl.class,
		PurposeDaoImpl.class, PatientDaoImpl.class, MedicalStaffDaoImpl.class })
public class UtilServiceImplTest {
	UtilServiceImpl service;
	DiagnosisData inputData;
	DiagnosisDaoImpl diagnosisDao;
	UtilDaoImpl utilDao;
	PurposeDaoImpl purposeDao;
	PatientDaoImpl patientDao;
	MedicalStaffDaoImpl medicalStaffDao;

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
		EasyMock.expect(UtilDaoImpl.getInstance()).andReturn(utilDao);
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
		service = UtilServiceImpl.getInstance();
		inputData = new DiagnosisData();
		inputData.setDate("1970-01-01");
		inputData.setName("correct text field");
	}

	@Test
	public void takeStaffRolesTestPositive()
			throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffRoles())
				.andReturn(Collections.emptyList());
		PowerMock.replayAll(utilDao);
		List<String> actual = service.takeStaffRoles();
		List<String> expected = Collections.emptyList();
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeStaffRolesTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffRoles())
				.andThrow(new DaoException("Failed to take staff roles"));
		PowerMock.replayAll(utilDao);
		service.takeStaffRoles();
	}

	@Test
	public void takeStaffSpecialitiesTestPositive()
			throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffSpecialities())
				.andReturn(Collections.emptyList());
		PowerMock.replayAll(utilDao);
		List<String> actual = service.takeStaffSpecialities();
		List<String> expected = Collections.emptyList();
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeStaffSpecialitiesTestNegative()
			throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffSpecialities())
				.andThrow(new DaoException("Failed to take staff specialities"));
		PowerMock.replayAll(utilDao);
		service.takeStaffSpecialities();
	}

}
