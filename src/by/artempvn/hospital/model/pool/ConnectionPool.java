package by.artempvn.hospital.model.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.hospital.exception.ConnectionException;

/**
 * The Class ConnectionPool.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ConnectionPool {
	private static final String URL_PROPERTIES = "url";
	private static final int POOL_SIZE = 8;
	private static ConnectionPool instance = new ConnectionPool();
	private BlockingQueue<ProxyConnection> involvedConnections;
	private BlockingQueue<ProxyConnection> freeConnections;
	private final Logger logger = LogManager.getLogger(ConnectionPool.class);

	/**
	 * Gets the single instance of ConnectionPool.
	 *
	 * @return single instance of ConnectionPool
	 */
	public static ConnectionPool getInstance() {
		return instance;
	}

	private ConnectionPool() {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		} catch (SQLException e) {
			logger.log(Level.FATAL, "Failed during registration of driver");
			throw new RuntimeException("Failed during registration of driver", e);
		}
		try {
			involvedConnections = new ArrayBlockingQueue<>(POOL_SIZE);
			freeConnections = new ArrayBlockingQueue<>(POOL_SIZE);
			PropertyReader reader = new PropertyReader();
			Properties properties = reader.readProperty();
			String url = properties.getProperty(URL_PROPERTIES);
			for (int i = 0; i < POOL_SIZE; i++) {
				try {
					Connection connection = DriverManager.getConnection(url, properties);
					freeConnections.add(new ProxyConnection(connection));
				} catch (SQLException e) {
					logger.log(Level.ERROR, "Failed during creating of connection");
				}
			}
			logger.log(Level.INFO,
					"Pool was created. Number of available  connections: "
							+ freeConnections.size());
			if (freeConnections.size() == 0) {
				throw new ConnectionException("Could't create any connection");
			}
		} catch (ConnectionException e) {
			logger.log(Level.FATAL, "Failed during creating of connection pool");
			throw new RuntimeException("Failed during creating of connection pool",
					e);
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		ProxyConnection connection = null;
		try {
			connection = freeConnections.take();
			involvedConnections.offer(connection);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "Failed during connection request");
		}
		return connection;
	}

	/**
	 * Return connection.
	 *
	 * @param connection the connection
	 */
	public void returnConnection(Connection connection) {
		if (connection instanceof ProxyConnection
				&& involvedConnections.remove(connection)) {
			freeConnections.offer((ProxyConnection) connection);
		} else {
			logger.log(Level.ERROR, "Failed to return connection to pool");
		}
	}

	/**
	 * Destroy pool.
	 *
	 * @throws ConnectionException the connection exception
	 */
	public void destroyPool() throws ConnectionException {
		try {
			for (int i = 0; i < POOL_SIZE; i++) {
				ProxyConnection connection = freeConnections.take();
				connection.finalClose();
			}
		} catch (SQLException | InterruptedException e) {
			throw new ConnectionException("Failed to close connection", e);
		} finally {
			try {
				deregisterDrivers();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Failed to deregister drivers");
			}
		}
	}

	private void deregisterDrivers() throws SQLException {
		while (DriverManager.getDrivers().hasMoreElements()) {
			Driver driver = DriverManager.getDrivers().nextElement();
			DriverManager.deregisterDriver(driver);
		}
		logger.log(Level.INFO, "Drivers were deregistered successfully");
	}

}
