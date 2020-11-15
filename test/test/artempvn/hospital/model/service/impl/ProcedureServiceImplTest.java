package test.artempvn.hospital.model.service.impl;

import static org.testng.Assert.assertEquals;
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
import by.artempvn.hospital.model.dao.impl.ProcedureDaoImpl;
import by.artempvn.hospital.model.dao.impl.PurposeDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.ProcedureData;
import by.artempvn.hospital.model.service.impl.ProcedureServiceImpl;

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
		ProcedureDaoImpl.class })
public class ProcedureServiceImplTest {
	ProcedureServiceImpl service;
	ProcedureData inputData;
	UtilDaoImpl utilDao;
	PurposeDaoImpl purposeDao;
	PatientDaoImpl patientDao;
	MedicalStaffDaoImpl medicalStaffDao;
	ProcedureDaoImpl procedureDao;

	@ObjectFactory
	public IObjectFactory setObjectFactory() {
		return new PowerMockObjectFactory();
	}

	@BeforeMethod
	public void setUp() {
		PowerMock.mockStatic(ProcedureDaoImpl.class);
		procedureDao = EasyMock.createNiceMock(ProcedureDaoImpl.class);
		EasyMock.expect(ProcedureDaoImpl.getInstance()).andReturn(procedureDao);
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
		service = ProcedureServiceImpl.getInstance();
		inputData = new ProcedureData();
		inputData.setName("test");
		inputData.setType("operation");
		inputData.setDateStart("1970-01-01T00:00:00");
		inputData.setDateEnd("1970-01-01T00:00:10");
	}

	@Test
	public void addProcedureTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeProcedureTypes())
				.andReturn(Arrays.asList("operation"));
		EasyMock.expect(utilDao.takePurposeIdByPatientId(EasyMock.anyLong()))
				.andReturn(1L);
		EasyMock.expect(utilDao.takeProcedureTypeId(EasyMock.anyObject()))
				.andReturn("1");
		EasyMock.expect(procedureDao.addProcedure(EasyMock.anyObject()))
				.andReturn(1L);
		EasyMock.expect(purposeDao.takeAttendingDoctorId(EasyMock.anyLong()))
				.andReturn(-1L);
		PowerMock.replayAll(utilDao, purposeDao, procedureDao);
		ProcedureData actual = service.addProcedure(inputData);
		ProcedureData expected = new ProcedureData();
		expected.setName("test");
		expected.setProcedureId(1);
		expected.setType("1");
		expected.setDateStart("0");
		expected.setDateEnd("10");
		expected.setCorrectData(true);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void addProcedureTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeProcedureTypes())
				.andThrow(new DaoException("Failed to add procedure"));
		PowerMock.replayAll(utilDao);
		service.addProcedure(inputData);
	}
	
}
