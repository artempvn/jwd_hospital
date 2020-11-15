package test.artempvn.hospital.model.service.impl;

import static org.testng.Assert.assertEquals;
import java.util.Arrays;
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
import by.artempvn.hospital.model.entity.DrugData;
import by.artempvn.hospital.model.entity.PatientData;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.service.impl.PatientServiceImpl;
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
		PurposeServiceImpl.class })
public class PatientServiceImplTest {
	PatientServiceImpl service;
	PurposeServiceImpl purposeService;
	DiagnosisDaoImpl diagnosisDao;
	UtilDaoImpl utilDao;
	PurposeDaoImpl purposeDao;
	PatientDaoImpl patientDao;
	MedicalStaffDaoImpl medicalStaffDao;
	PatientData patient;

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
		PowerMock.mockStatic(PurposeServiceImpl.class);
		purposeService = EasyMock.createNiceMock(PurposeServiceImpl.class);
		EasyMock.expect(PurposeServiceImpl.getInstance()).andReturn(purposeService);
		service = PatientServiceImpl.getInstance();
		patient = new PatientData();
		patient.setAdmissionDate("1970-01-01");
		patient.setAdmissionDiagnosis("test");
		patient.setDateBirth("1970-01-01");
		patient.setName("test");
		patient.setSecondName("test");
		patient.setSurname("test");
		patient.setStatus("registered");
		patient.setDiagnosisId(1L);
	}

	@Test
	public void addPatientTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takePatientStatusId(EasyMock.anyObject()))
				.andReturn("1");
		PowerMock.replayAll(utilDao);
		PatientData actual = service.addPatient(patient);
		PatientData expected = new PatientData();
		expected.setAdmissionDate("0");
		expected.setAdmissionDiagnosis("test");
		expected.setDateBirth("0");
		expected.setName("test");
		expected.setSecondName("test");
		expected.setSurname("test");
		expected.setStatus("1");
		expected.setCorrectData(true);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void addPatientTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takePatientStatusId(EasyMock.anyObject()))
				.andThrow(new DaoException("Failed to add patient"));
		PowerMock.replayAll(utilDao);
		service.addPatient(patient);
	}

	@Test
	public void takePatientsTestPositive() throws ServiceException, DaoException {
		List<PatientData> patients = Arrays.asList(patient);
		EasyMock.expect(patientDao.takePatients()).andReturn(patients);
		EasyMock.expect(utilDao.takePurposeIdByPatientId(EasyMock.anyLong()))
				.andReturn(1L);
		EasyMock.expect(purposeDao.takeDrugs(EasyMock.anyLong()))
				.andReturn(List.of(new DrugData()));
		EasyMock.expect(purposeDao.takeAttendingDoctorId(EasyMock.anyLong()))
				.andReturn(1L);
		UserData doctor = new UserData();
		EasyMock.expect(medicalStaffDao.takeMedicalStaffById(EasyMock.anyLong()))
				.andReturn(doctor);
		PowerMock.replayAll(patientDao, utilDao, purposeDao, medicalStaffDao);
		List<PatientData> actual = service.takePatients();
		PatientData expectedPatient = new PatientData();
		expectedPatient.setAdmissionDate("1970-01-01");
		expectedPatient.setAdmissionDiagnosis("test");
		expectedPatient.setDateBirth("1970-01-01");
		expectedPatient.setName("test");
		expectedPatient.setSecondName("test");
		expectedPatient.setSurname("test");
		expectedPatient.setStatus("registered");
		expectedPatient.setDiagnosisId(1L);
		expectedPatient.setAnyPurpose(true);
		expectedPatient.setAttendingDoctor(doctor);
		List<PatientData> expected = List.of(expectedPatient);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takePatientsTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(patientDao.takePatients())
				.andThrow(new DaoException("Failed to take patients"));
		PowerMock.replayAll(patientDao);
		service.takePatients();
	}

	@Test
	public void dischargePatientTestPositive()
			throws ServiceException, DaoException {
		PatientData patientForDischarge = new PatientData();
		patientForDischarge.setStatus("on_medication");
		patientForDischarge.setTreatmentResult("test");
		patientForDischarge.setDischargeDate("1970-01-02");
		EasyMock.expect(patientDao.takePatient(EasyMock.anyLong()))
				.andReturn(patientForDischarge);
		EasyMock.expect(purposeService.takeLastPurposeDate(EasyMock.anyLong()))
				.andReturn(0L);
		PowerMock.replayAll(patientDao, purposeService);
		PatientData actual = service.dischargePatient(patientForDischarge);
		PatientData expected = new PatientData();
		expected.setDischargeDate("1");
		expected.setTreatmentResult("test");
		expected.setDischarged(true);
		expected.setCorrectData(true);
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void dischargePatientTestNegative()
			throws ServiceException, DaoException {
		PatientData patientForDischarge = new PatientData();
		patientForDischarge.setStatus("on_medication");
		patientForDischarge.setTreatmentResult("test");
		patientForDischarge.setDischargeDate("1970-01-02");
		EasyMock.expect(patientDao.takePatient(EasyMock.anyLong()))
				.andThrow(new DaoException("Failed to discharge patient"));
		PowerMock.replayAll(patientDao);
		service.dischargePatient(patientForDischarge);
	}

	@Test
	public void takePatientTestPositive() throws ServiceException, DaoException {
		PatientData somePatient = new PatientData();
		EasyMock.expect(patientDao.takePatient(EasyMock.anyLong()))
				.andReturn(somePatient);
		PowerMock.replayAll(patientDao);
		PatientData actual = service.takePatient(1L);
		PatientData expected = new PatientData();
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takePatientTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(patientDao.takePatient(EasyMock.anyLong()))
				.andThrow(new DaoException("Failed to take patient"));
		PowerMock.replayAll(patientDao);
		service.takePatient(1L);
	}

}
