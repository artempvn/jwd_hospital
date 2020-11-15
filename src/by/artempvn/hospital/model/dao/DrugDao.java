package by.artempvn.hospital.model.dao;

import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.entity.DrugData;

/**
 * The Interface DrugDao.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface DrugDao {

	/**
	 * Adds the drug.
	 *
	 * @param data the data
	 * @return the long
	 * @throws DaoException the dao exception
	 */
	long addDrug(DrugData data) throws DaoException;

}
