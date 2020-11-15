package by.artempvn.hospital.model.entity;

/**
 * The Class UserData.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class UserData {

	private static final String DEFAULT_STATUS = "registered";
	private String login;
	private String email;
	private String name;
	private String surname;
	private String secondName;
	private String role;
	private String speciality;
	private String password;
	private String status = DEFAULT_STATUS;
	private boolean isCorrectData;
	private boolean isAdded;

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Gets the speciality.
	 *
	 * @return the speciality
	 */
	public String getSpeciality() {
		return speciality;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Sets the speciality.
	 *
	 * @param speciality the new speciality
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Checks if is correct data.
	 *
	 * @return true, if is correct data
	 */
	public boolean isCorrectData() {
		return isCorrectData;
	}

	/**
	 * Sets the correct data.
	 *
	 * @param isCorrectData the new correct data
	 */
	public void setCorrectData(boolean isCorrectData) {
		this.isCorrectData = isCorrectData;
	}

	/**
	 * Checks if is added.
	 *
	 * @return true, if is added
	 */
	public boolean isAdded() {
		return isAdded;
	}

	/**
	 * Sets the added.
	 *
	 * @param isAdded the new added
	 */
	public void setAdded(boolean isAdded) {
		this.isAdded = isAdded;
	}

	/**
	 * Gets the second name.
	 *
	 * @return the second name
	 */
	public String getSecondName() {
		return secondName;
	}

	/**
	 * Sets the second name.
	 *
	 * @param secondName the new second name
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (isAdded ? 1231 : 1237);
		result = prime * result + (isCorrectData ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result
				+ ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result
				+ ((speciality == null) ? 0 : speciality.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserData other = (UserData) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (isAdded != other.isAdded) {
			return false;
		}
		if (isCorrectData != other.isCorrectData) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (secondName == null) {
			if (other.secondName != null) {
				return false;
			}
		} else if (!secondName.equals(other.secondName)) {
			return false;
		}
		if (speciality == null) {
			if (other.speciality != null) {
				return false;
			}
		} else if (!speciality.equals(other.speciality)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (surname == null) {
			if (other.surname != null) {
				return false;
			}
		} else if (!surname.equals(other.surname)) {
			return false;
		}
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserData [login=").append(login).append(", email=")
				.append(email).append(", name=").append(name).append(", surname=")
				.append(surname).append(", secondName=").append(secondName)
				.append(", role=").append(role).append(", speciality=")
				.append(speciality).append(", password=").append(password)
				.append(", status=").append(status).append(", isCorrectData=")
				.append(isCorrectData).append(", isAdded=").append(isAdded).append("]");
		return builder.toString();
	}

}
