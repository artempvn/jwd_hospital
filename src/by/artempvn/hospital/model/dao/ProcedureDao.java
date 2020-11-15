package by.artempvn.hospital.model.dao;

import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.entity.ProcedureData;

/**
 * The Interface ProcedureDao.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public interface ProcedureDao {

	/**
	 * Adds the procedure.
	 *
	 * @param data the data
	 * @return the long
	 * @throws DaoException the dao exception
	 */
	long addProcedure(ProcedureData data) throws DaoException;

}
