package by.artempvn.hospital.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.dao.DrugDao;
import by.artempvn.hospital.model.entity.DrugData;
import by.artempvn.hospital.model.pool.ConnectionPool;

/**
 * The Class DrugDaoImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DrugDaoImpl implements DrugDao {
	private static final String SQL_ADD_DRUG = "INSERT INTO drugs "
			+ "(drug_name,amount,appointment_time) VALUES (?,?,?)";
	private static DrugDaoImpl instance = new DrugDaoImpl();

	private DrugDaoImpl() {
	}

	public static DrugDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long addDrug(DrugData data) throws DaoException {
		long id;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_DRUG,
						Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, data.getName());
			statement.setString(2, data.getAmount());
			statement.setString(3, data.getAppointmentTime());
			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
			result.next();
			id = result.getLong(1);
		} catch (SQLException ex) {
			throw new DaoException("Failed to add drug purpose", ex);
		}
		return id;
	}

}
