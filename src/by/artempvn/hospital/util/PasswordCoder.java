package by.artempvn.hospital.util;

/**
 * The Class PasswordCoder.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class PasswordCoder {

	private PasswordCoder() {
	}

	/**
	 * Code password.
	 *
	 * @param inputPassword the input password
	 * @return the string
	 */
	public static String codePassword(String inputPassword) {
		if (inputPassword == null) {
			return new String();
		}
		char[] password = inputPassword.toCharArray();
		int[] cryptArray = pass1(password);
		pass2(cryptArray);
		return arrayToString(cryptArray);
	}

	private static int[] pass1(char[] password) {
		int[] cryptArray = new int[password.length];
		for (int i = 0; i < password.length; i++) {
			cryptArray[i] = password[i];
		}
		return cryptArray;
	}

	private static void pass2(int[] password) {
		for (int i = 0; i < password.length; i++) {
			password[i] = Integer.parseInt(Integer.toOctalString(password[i]));
		}
	}

	private static String arrayToString(int[] array) {
		StringBuilder password = new StringBuilder();
		for (int number : array) {
			password.append(number);
		}
		return password.toString();
	}

}
