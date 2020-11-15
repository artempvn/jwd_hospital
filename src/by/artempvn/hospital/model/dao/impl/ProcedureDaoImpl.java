package by.artempvn.hospital.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.dao.ProcedureDao;
import by.artempvn.hospital.model.entity.ProcedureData;
import by.artempvn.hospital.model.pool.ConnectionPool;

/**
 * The Class ProcedureDaoImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ProcedureDaoImpl implements ProcedureDao {
	private static final String SQL_ADD_PROCEDURE = "INSERT INTO procedures "
			+ "(procedure_name,date_start,date_end,procedure_type_id_fk) VALUES (?,?,?,?)";
	private static ProcedureDaoImpl instance = new ProcedureDaoImpl();

	private ProcedureDaoImpl() {
	}

	public static ProcedureDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long addProcedure(ProcedureData data) throws DaoException {
		int id;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						SQL_ADD_PROCEDURE, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, data.getName());
			statement.setLong(2, Long.parseLong(data.getDateStart()));
			statement.setLong(3, Long.parseLong(data.getDateEnd()));
			statement.setByte(4, Byte.parseByte(data.getType()));
			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
			result.next();
			id = result.getInt(1);
		} catch (SQLException ex) {
			throw new DaoException("Failed to add procedure purpose", ex);
		}
		return id;
	}

}
