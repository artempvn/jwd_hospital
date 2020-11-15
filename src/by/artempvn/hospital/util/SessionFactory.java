package by.artempvn.hospital.util;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * A factory for creating Session objects.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class SessionFactory {
	private static final String MAIL_USER_PASSWORD = "mail.user.password";
	private static final String MAIL_USER_NAME = "mail.user.name";

	/**
	 * Creates a new Session object.
	 *
	 * @param configProperties the config properties
	 * @return the session
	 */
	public static Session createSession(Properties configProperties) {
		String userName = configProperties.getProperty(MAIL_USER_NAME);
		String userPassword = configProperties.getProperty(MAIL_USER_PASSWORD);
		return Session.getInstance(configProperties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userName, userPassword);
					}
				});
	}

}
