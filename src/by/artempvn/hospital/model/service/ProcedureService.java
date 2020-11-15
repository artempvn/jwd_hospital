package by.artempvn.hospital.model.service;

import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.ProcedureData;

/**
 * The Interface ProcedureService.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface ProcedureService {

	/**
	 * Adds the procedure.
	 *
	 * @param data the data
	 * @return the procedure data
	 * @throws ServiceException the service exception
	 */
	ProcedureData addProcedure(ProcedureData data) throws ServiceException;

}
