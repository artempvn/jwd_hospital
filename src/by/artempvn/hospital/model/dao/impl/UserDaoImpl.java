package by.artempvn.hospital.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.exception.DaoException;
import by.artempvn.hospital.model.dao.ColumnName;
import by.artempvn.hospital.model.dao.UserDao;
import by.artempvn.hospital.model.entity.UserData;
import by.artempvn.hospital.model.pool.ConnectionPool;

/**
 * The Class UserDaoImpl.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class UserDaoImpl implements UserDao {
	private static final String SQL_UNIQUE_LOGIN = "SELECT login "
			+ "FROM staff WHERE login=?";
	private static final String SQL_UNIQUE_EMAIL = "SELECT email "
			+ "FROM staff WHERE email=?";
	private static final String SQL_PASSWORD = "SELECT password "
			+ "FROM staff WHERE login=?";
	private static final String SQL_STAFF_ID_BY_LOGIN = "SELECT staff_id "
			+ "FROM staff WHERE login=?";
	private static final String SQL_ROLE = "SELECT role_type FROM staff "
			+ "JOIN roles ON role_id_fk=role_id WHERE login=?";
	private static final String SQL_ADD_USER_STAFF = "INSERT INTO staff "
			+ "(login,password,email,role_id_fk,staff_status_id_fk) VALUES (?,?,?,?,?)";
	private static final String SQL_ADD_USER_MEDICAL_STAFF = "INSERT "
			+ "INTO medical_staff (staff_id_fk,staff_name,staff_surname,"
			+ "staff_second_name,speciality_id_fk) VALUES (?,?,?,?,?) ";
	private static final String SQL_ACTIVATE_USER = "UPDATE staff "
			+ "SET staff_status_id_fk=2 WHERE (login=? AND staff_status_id_fk=1)";
	private static final String SQL_STATUS = "SELECT staff_status_type FROM staff"
			+ " JOIN staff_statuses ON staff_status_id_fk=staff_status_id WHERE login=?";
	private static final String SQL_ROLE_BY_ID = "SELECT role_type FROM staff "
			+ "JOIN roles ON role_id_fk=role_id WHERE staff_id=?";
	private static final String SQL_UPDATE_USER_STATUS = "UPDATE staff "
			+ "SET staff_status_id_fk=? WHERE login=?";
	private static UserDaoImpl instance = new UserDaoImpl();
	private final Logger logger = LogManager.getLogger(UserDaoImpl.class);

	private UserDaoImpl() {
	}

	public static UserDaoImpl getInstance() {
		return instance;
	}

	@Override
	public String takeUserPassword(String login) throws DaoException {
		String password = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_PASSWORD)) {
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				password = resultSet.getString(ColumnName.STAFF_PASSWORD);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to find user password", ex);
		}
		return password;
	}

	@Override
	public String takeUserRole(String login) throws DaoException {
		String role = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ROLE)) {
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				role = resultSet.getString(ColumnName.ROLE_TYPE);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to find user role", ex);
		}
		return role;
	}

	@Override
	public boolean addUser(UserData data) throws DaoException {
		boolean isAdded = true;
		if (isLoginEmailUnique(data)) {
			logger.log(Level.DEBUG, "Login and email are unique");
			Connection connection = null;
			try {
				connection = ConnectionPool.getInstance().getConnection();
				connection.setAutoCommit(false);
				try (
						PreparedStatement statementStaff = connection
								.prepareStatement(SQL_ADD_USER_STAFF);
						PreparedStatement statementMedicalStaff = connection
								.prepareStatement(SQL_ADD_USER_MEDICAL_STAFF);
						PreparedStatement statementId = connection
								.prepareStatement(SQL_STAFF_ID_BY_LOGIN)) {
					statementStaff.setString(1, data.getLogin());
					statementStaff.setString(2, data.getPassword());
					statementStaff.setString(3, data.getEmail());
					statementStaff.setByte(4, Byte.parseByte(data.getRole()));
					statementStaff.setByte(5, Byte.parseByte(data.getStatus()));
					statementStaff.execute();
					statementId.setString(1, data.getLogin());
					ResultSet resultSet = statementId.executeQuery();
					long id = 0;
					if (resultSet.next()) {
						id = resultSet.getLong(ColumnName.STAFF_ID);
					}
					statementMedicalStaff.setLong(1, id);
					statementMedicalStaff.setString(2, data.getName());
					statementMedicalStaff.setString(3, data.getSurname());
					statementMedicalStaff.setString(4, data.getSecondName());
					statementMedicalStaff.setByte(5,
							Byte.parseByte(data.getSpeciality()));
					statementMedicalStaff.execute();
					connection.commit();
				} catch (SQLException ex) {
					try {
						if (connection != null) {
							connection.rollback();
						}
					} catch (SQLException e) {
						logger.log(Level.ERROR, "Failed to roll back transaction");
					}
					throw new DaoException("Failed to add user", ex);
				}
			} catch (SQLException ex) {
				throw new DaoException("Failed to add user", ex);
			} finally {
				if (connection != null) {
					try {
						connection.setAutoCommit(true);
					} catch (SQLException ex) {
						logger.log(Level.ERROR,
								"Failed to set auto-commit mode for connection");
					} finally {
						try {
							connection.close();
						} catch (SQLException ex) {
							logger.log(Level.ERROR, "Impossible exception");
						}
					}
				}
			}
		} else {
			isAdded = false;
		}
		return isAdded;
	}

	private boolean isLoginEmailUnique(UserData data) throws DaoException {
		boolean isUnique = true;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement loginStatement = connection
						.prepareStatement(SQL_UNIQUE_LOGIN);
				PreparedStatement emailStatement = connection
						.prepareStatement(SQL_UNIQUE_EMAIL)) {
			loginStatement.setString(1, data.getLogin());
			emailStatement.setString(1, data.getEmail());
			ResultSet loginResultSet = loginStatement.executeQuery();
			ResultSet emailResultSet = emailStatement.executeQuery();
			if (loginResultSet.next()) {
				logger.log(Level.WARN, "Login is not unique");
				isUnique = false;
			}
			if (emailResultSet.next()) {
				logger.log(Level.WARN, "Email is not unique");
				isUnique = false;
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to check login/email for unique value",
					ex);
		}
		return isUnique;
	}

	@Override
	public boolean activateUser(String login) throws DaoException {
		int numberOfChangedStrings;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_ACTIVATE_USER)) {
			statement.setString(1, login);
			numberOfChangedStrings = statement.executeUpdate();
		} catch (SQLException ex) {
			throw new DaoException("Failed to change user status", ex);
		}
		return numberOfChangedStrings == 1 ? true : false;
	}

	@Override
	public String takeUserStatus(String login) throws DaoException {
		String status = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATUS)) {
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				status = resultSet.getString(ColumnName.STAFF_STATUS_TYPE);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to find user role", ex);
		}
		return status;
	}

	@Override
	public String takeUserRole(long id) throws DaoException {
		String role = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_ROLE_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				role = resultSet.getString(ColumnName.ROLE_TYPE);
			}
		} catch (SQLException ex) {
			throw new DaoException("Failed to find user role", ex);
		}
		return role;
	}

	@Override
	public void updateUserStatus(String login, byte userStatusId)
			throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_UPDATE_USER_STATUS)) {
			statement.setByte(1, userStatusId);
			statement.setString(2, login);
			statement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Failed to change user status", ex);
		}
	}

}
