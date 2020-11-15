package by.artempvn.hospital.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.dao.ColumnName;
import by.artempvn.hospital.model.dao.MedicalStaffDao;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.pool.ConnectionPool;

/**
 * The Class MedicalStaffDaoImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class MedicalStaffDaoImpl implements MedicalStaffDao {
	private static final String SQL_STAFF_DATA = "SELECT login,email,staff_name,"
			+ "staff_surname,role_type,speciality_type,staff_status_type "
			+ "FROM staff JOIN medical_staff ON staff_id=staff_id_fk JOIN roles "
			+ "ON role_id_fk=role_id JOIN specialities ON speciality_id_fk=speciality_id "
			+ "JOIN staff_statuses ON staff_status_id_fk=staff_status_id "
			+ "ORDER BY staff_status_id_fk,role_id_fk,staff_surname,staff_name";
	private static final String SQL_ACTIVE_STAFF_DATA = "SELECT login,email,"
			+ "staff_name,staff_surname,role_type,speciality_type,staff_status_type "
			+ "FROM staff JOIN medical_staff ON staff_id=staff_id_fk JOIN roles "
			+ "ON role_id_fk=role_id JOIN specialities ON speciality_id_fk=speciality_id "
			+ "JOIN staff_statuses ON staff_status_id_fk=staff_status_id "
			+ "WHERE staff_status_type='activated' ORDER BY staff_name";
	private static final String SQL_STAFF_DATA_BY_ID = "SELECT login,email,staff_name,"
			+ "staff_surname,role_type,speciality_type,staff_status_type,staff_second_name "
			+ "FROM staff JOIN medical_staff ON staff_id=staff_id_fk JOIN roles "
			+ "ON role_id_fk=role_id JOIN specialities ON speciality_id_fk=speciality_id "
			+ "JOIN staff_statuses ON staff_status_id_fk=staff_status_id WHERE staff_id_fk=?";
	private static MedicalStaffDaoImpl instance = new MedicalStaffDaoImpl();

	private MedicalStaffDaoImpl() {
	}

	public static MedicalStaffDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<UserData> takeMedicalStaff(boolean takeOnlyActive)
			throws DaoException {
		List<UserData> staff = new ArrayList<>();
		String sql = takeOnlyActive ? SQL_ACTIVE_STAFF_DATA : SQL_STAFF_DATA;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String login = resultSet.getString(ColumnName.STAFF_LOGIN);
				String email = resultSet.getString(ColumnName.STAFF_EMAIL);
				String name = resultSet.getString(ColumnName.MEDICAL_STAFF_NAME);
				String surname = resultSet.getString(ColumnName.MEDICAL_STAFF_SURNAME);
				String role = resultSet.getString(ColumnName.ROLE_TYPE);
				String speciality = resultSet.getString(ColumnName.SPECIALITY_TYPE);
				String status = resultSet.getString(ColumnName.STAFF_STATUS_TYPE);
				UserData user = new UserData();
				user.setLogin(login);
				user.setEmail(email);
				user.setName(name);
				user.setSurname(surname);
				user.setRole(role);
				user.setSpeciality(speciality);
				user.setStatus(status);
				staff.add(user);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take staff data", ex);
		}
		return staff;
	}

	@Override
	public UserData takeMedicalStaffById(long userId) throws DaoException {
		UserData user = new UserData();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_STAFF_DATA_BY_ID)) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String login = resultSet.getString(ColumnName.STAFF_LOGIN);
				String email = resultSet.getString(ColumnName.STAFF_EMAIL);
				String name = resultSet.getString(ColumnName.MEDICAL_STAFF_NAME);
				String surname = resultSet.getString(ColumnName.MEDICAL_STAFF_SURNAME);
				String role = resultSet.getString(ColumnName.ROLE_TYPE);
				String speciality = resultSet.getString(ColumnName.SPECIALITY_TYPE);
				String status = resultSet.getString(ColumnName.STAFF_STATUS_TYPE);
				String secondName = resultSet
						.getString(ColumnName.MEDICAL_STAFF_SECOND_NAME);
				user.setLogin(login);
				user.setEmail(email);
				user.setName(name);
				user.setSurname(surname);
				user.setRole(role);
				user.setSpeciality(speciality);
				user.setStatus(status);
				user.setSecondName(secondName);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to take staff data by id", ex);
		}
		return user;
	}

}
