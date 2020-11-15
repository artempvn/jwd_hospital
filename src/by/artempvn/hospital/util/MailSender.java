package by.artempvn.hospital.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class MailSender.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class MailSender {
	private static final String TEXT_HTML = "text/html";
	private static final String MAIL_SUBJECT = "JWD Hosptital Activation";
	private static final String MAIL_PROPERTIES = "properties\\mail.properties";
	private MimeMessage message;
	private String sendToEmail;
	private String mailText;

	private final Logger logger = LogManager.getLogger(MailSender.class);

	/**
	 * Instantiates a new mail sender.
	 *
	 * @param sendToEmail the send to email
	 * @param mailText the mail text
	 */
	public MailSender(String sendToEmail, String mailText) {
		this.sendToEmail = sendToEmail;
		this.mailText = mailText;
	}

	/**
	 * Send.
	 */
	public void send() {
		try {
			initMessage();
			Transport.send(message);
			logger.log(Level.INFO, "Email was successfuly sent");
		} catch (AddressException e) {
			logger.log(Level.ERROR, "Invalid address: " + sendToEmail + "  " + e);
		} catch (MessagingException e) {
			logger.log(Level.ERROR, "Error generating or sending message: " + e);
		}
	}

	private void initMessage() throws MessagingException {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(MAIL_PROPERTIES));
			Session mailSession = SessionFactory.createSession(properties);
			mailSession.setDebug(false);
			message = new MimeMessage(mailSession);
			message.setSubject(MAIL_SUBJECT);
			message.setContent(mailText, TEXT_HTML);
			message.setRecipient(Message.RecipientType.TO,
					new InternetAddress(sendToEmail));
		} catch (IOException e) {
			logger.log(Level.ERROR, "Couldn't open email properties: " + e);
		}
	}

}
