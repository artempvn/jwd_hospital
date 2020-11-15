package by.artempvn.hospital.model.service;

import by.artempvn.hospital.exception.ServiceException;
import by.artempvn.hospital.model.entity.DrugData;

/**
 * The Interface DrugService.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface DrugService {

	/**
	 * Adds the drug.
	 *
	 * @param data the data
	 * @return the drug data
	 * @throws ServiceException the service exception
	 */
	DrugData addDrug(DrugData data) throws ServiceException;

}
