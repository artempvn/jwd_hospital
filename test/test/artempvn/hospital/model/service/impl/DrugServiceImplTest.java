package test.artempvn.hospital.model.service.impl;

import static org.testng.Assert.assertEquals;
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
import by.artempvn.hospital.model.dao.impl.DrugDaoImpl;
import by.artempvn.hospital.model.dao.impl.PurposeDaoImpl;
import by.artempvn.hospital.model.dao.impl.UtilDaoImpl;
import by.artempvn.hospital.model.entity.DrugData;
import by.artempvn.hospital.model.service.impl.DrugServiceImpl;

/**
 * The Class DataValidatorTest.
 * 
 * @author Artem Piven
 * @version 1.0
 */
@PowerMockIgnore({ "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*",
		"org.w3c.*", "com.sun.org.apache.xalan.*", "javax.management.*" })
@PrepareForTest({ PurposeDaoImpl.class, DrugDaoImpl.class, UtilDaoImpl.class })
public class DrugServiceImplTest {
	DrugServiceImpl service;
	UtilDaoImpl utilDao;
	PurposeDaoImpl purposeDao;
	DrugDaoImpl drugDao;
	DrugData inputData;

	@ObjectFactory
	public IObjectFactory setObjectFactory() {
		return new PowerMockObjectFactory();
	}

	@BeforeMethod
	public void setUp() {
		PowerMock.mockStatic(PurposeDaoImpl.class);
		purposeDao = EasyMock.createNiceMock(PurposeDaoImpl.class);
		EasyMock.expect(PurposeDaoImpl.getInstance()).andReturn(purposeDao);
		PowerMock.mockStatic(DrugDaoImpl.class);
		drugDao = EasyMock.createNiceMock(DrugDaoImpl.class);
		EasyMock.expect(DrugDaoImpl.getInstance()).andReturn(drugDao);
		PowerMock.mockStatic(UtilDaoImpl.class);
		utilDao = EasyMock.createNiceMock(UtilDaoImpl.class);
		EasyMock.expect(UtilDaoImpl.getInstance()).andReturn(utilDao);
		service = DrugServiceImpl.getInstance();
		inputData = new DrugData();
		inputData.setAmount("test");
		inputData.setName("test");
		inputData.setAppointmentTime("test");

	}

	@Test
	public void addDrugTestPositive() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takePurposeIdByPatientId(EasyMock.anyLong()))
				.andReturn(1L);
		EasyMock.expect(drugDao.addDrug(EasyMock.anyObject())).andReturn(1L);
		PowerMock.replayAll(utilDao, drugDao);
		DrugData actual = service.addDrug(inputData);
		DrugData expected = new DrugData();
		expected.setCorrectData(true);
		expected.setDrugId(1);
		expected.setAmount("test");
		expected.setName("test");
		expected.setAppointmentTime("test");
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void addDrugTestNegative() throws ServiceException, DaoException {
		EasyMock.expect(utilDao.takePurposeIdByPatientId(EasyMock.anyLong()))
				.andThrow(new DaoException("Failed to add drug"));
		PowerMock.replayAll(utilDao);
		service.addDrug(inputData);
	}

}
