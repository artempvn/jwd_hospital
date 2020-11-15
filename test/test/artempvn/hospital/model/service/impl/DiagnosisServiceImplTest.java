package test.artempvn.hospital.model.service.impl;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
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
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.DiagnosisData;
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.impl.DiagnosisServiceImpl;

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
public class DiagnosisServiceImplTest {
	DiagnosisServiceImpl service;
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
		service = DiagnosisServiceImpl.getInstance();
		inputData = new DiagnosisData();
		inputData.setDate("1970-01-01");
		inputData.setName("correct text field");
	}

	@Test
	public void addDiagnosisTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(diagnosisDao.addDiagnosis(EasyMock.anyObject()))
				.andReturn(1L);
		EasyMock.expect(utilDao.takeStaffIdByLogin(EasyMock.anyObject()))
				.andReturn("1");
		EasyMock.expect(purposeDao.addPurpose()).andReturn(1L);
		PowerMock.replayAll(utilDao, purposeDao, diagnosisDao, patientDao);
		DiagnosisData actual = service.addDiagnosis(inputData);
		DiagnosisData expected = new DiagnosisData();
		expected.setCorrectData(true);
		expected.setDate("0");
		expected.setDiagnosisId(1);
		expected.setEstablishedDoctor("1");
		expected.setName("correct text field");
		expected.setPurposeId(1);
		inputData.setEstablishedDoctor("1");
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void addDiagnosisTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takeStaffIdByLogin(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to add diagnosis"));
		PowerMock.replayAll(utilDao);
		service.addDiagnosis(inputData);
	}

	@Test
	public void takeDiagnosisDoctorDataTestPositive()
			throws ServiceException, DaoException {
		PatientData patient = new PatientData();
		patient.setDiagnosisId(1L);
		EasyMock.expect(patientDao.takePatient(EasyMock.anyLong()))
				.andReturn(patient);
		DiagnosisData diagnosis = new DiagnosisData();
		diagnosis.setEstablishedDoctor("1");
		EasyMock.expect(diagnosisDao.takeDiagnosis(EasyMock.anyLong()))
				.andReturn(diagnosis);
		UserData doctor = new UserData();
		EasyMock.expect(medicalStaffDao.takeMedicalStaffById(EasyMock.anyLong()))
				.andReturn(doctor);
		PowerMock.replayAll(medicalStaffDao, diagnosisDao, patientDao);
		Map<String, Object> actual = service.takeDiagnosisDoctorData(1L);
		Map<String, Object> expected = new HashMap<>();
		expected.put("diagnosis", diagnosis);
		expected.put("established_doctor", doctor);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeDiagnosisDoctorDataTestNegative()
			throws ServiceException, DaoException {
		PatientData patient = new PatientData();
		patient.setDiagnosisId(1L);
		EasyMock.expect(patientDao.takePatient(EasyMock.anyLong()))
				.andThrow(new DaoException("Failed to take diagnosis&doctor data"));
		PowerMock.replayAll(patientDao);
		service.takeDiagnosisDoctorData(1L);
	}

	@Test
	public void updateDiagnosisTestPositive()
			throws ServiceException, DaoException {
		PowerMock.replayAll(diagnosisDao);
		boolean actual = service.updateDiagnosis(inputData);
		boolean expected = true;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void updateDiagnosisTestNegative()
			throws ServiceException, DaoException {
		diagnosisDao.updateDiagnosis(EasyMock.anyObject());
		EasyMock.expectLastCall()
				.andThrow(new DaoException("Failed to update diagnosis"));
		PowerMock.replayAll(diagnosisDao);
		service.updateDiagnosis(inputData);
	}

}
