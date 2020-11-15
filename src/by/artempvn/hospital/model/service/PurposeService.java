package by.artempvn.hospital.model.service;

import java.util.Map;
import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.PurposeData;

/**
 * The Interface PurposeService.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface PurposeService {

	/**
	 * Change attending doctor.
	 *
	 * @param attendingDoctorLogin the attending doctor login
	 * @param patientId the patient id
	 * @return the purpose data
	 * @throws ServiceException the service exception
	 */
	PurposeData changeAttendingDoctor(String attendingDoctorLogin, long patientId)
			throws ServiceException;

	/**
	 * Adds the drug purpose.
	 *
	 * @param purposeId the purpose id
	 * @param drugId the drug id
	 * @throws ServiceException the service exception
	 */
	void addDrugPurpose(long purposeId, long drugId) throws ServiceException;

	/**
	 * Adds the procedure purpose.
	 *
	 * @param purposeId the purpose id
	 * @param procedureId the procedure id
	 * @throws ServiceException the service exception
	 */
	void addProcedurePurpose(long purposeId, long procedureId)
			throws ServiceException;

	/**
	 * Take last purpose date.
	 *
	 * @param purposeId the purpose id
	 * @return the long
	 * @throws ServiceException the service exception
	 */
	long takeLastPurposeDate(long purposeId) throws ServiceException;

	/**
	 * Take purpose.
	 *
	 * @param patientId the patient id
	 * @return the map
	 * @throws ServiceException the service exception
	 */
	Map<String, Object> takePurpose(long patientId) throws ServiceException;

	/**
	 * Take purposes.
	 *
	 * @param userLogin the user login
	 * @return the map
	 * @throws ServiceException the service exception
	 */
	Map<String, Object> takePurposes(String userLogin) throws ServiceException;

	/**
	 * Delete drug purpose.
	 *
	 * @param drugId the drug id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean deleteDrugPurpose(long drugId) throws ServiceException;

	/**
	 * Delete procedure purpose.
	 *
	 * @param procedureId the procedure id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean deleteProcedurePurpose(long procedureId) throws ServiceException;

}
