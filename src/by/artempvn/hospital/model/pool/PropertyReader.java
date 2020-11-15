package by.artempvn.hospital.model.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import by.artempvn.hospital.exception.ConnectionException;

/**
 * The Class PropertyReader.
 * 
 * @author Artem Piven
 * @version 1.0
 */
class PropertyReader {
	private static final String DATABASE_PROPERTIES_PATH = "properties\\hospital.properties";

	Properties readProperty() throws ConnectionException {
		Properties properties = new Properties();
		try (FileInputStream inputStream = new FileInputStream(
				DATABASE_PROPERTIES_PATH)) {
			properties.load(inputStream);
		} catch (IOException ex) {
			throw new ConnectionException("Failed to open properties file", ex);
		}
		return properties;
	}

}
