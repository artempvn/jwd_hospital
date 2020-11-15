package by.artempvn.hospital.model.entity;

/**
 * The Class DrugData.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DrugData {
	private String name;
	private String amount;
	private String appointmentTime;
	private boolean isCorrectData;
	private long drugId;
	private long patientId;
	private boolean isDiagnosisEstablished = true;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * Gets the appointment time.
	 *
	 * @return the appointment time
	 */
	public String getAppointmentTime() {
		return appointmentTime;
	}

	/**
	 * Sets the appointment time.
	 *
	 * @param date the new appointment time
	 */
	public void setAppointmentTime(String date) {
		this.appointmentTime = date;
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
	 * Gets the drug id.
	 *
	 * @return the drug id
	 */
	public long getDrugId() {
		return drugId;
	}

	/**
	 * Sets the drug id.
	 *
	 * @param drugId the new drug id
	 */
	public void setDrugId(long drugId) {
		this.drugId = drugId;
	}

	/**
	 * Gets the patient id.
	 *
	 * @return the patient id
	 */
	public long getPatientId() {
		return patientId;
	}

	/**
	 * Sets the patient id.
	 *
	 * @param patientId the new patient id
	 */
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	/**
	 * Checks if is diagnosis established.
	 *
	 * @return true, if is diagnosis established
	 */
	public boolean isDiagnosisEstablished() {
		return isDiagnosisEstablished;
	}

	/**
	 * Sets the diagnosis established.
	 *
	 * @param isDiagnosisEstablished the new diagnosis established
	 */
	public void setDiagnosisEstablished(boolean isDiagnosisEstablished) {
		this.isDiagnosisEstablished = isDiagnosisEstablished;
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
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((appointmentTime == null) ? 0 : appointmentTime.hashCode());
		result = prime * result + (int) (drugId ^ (drugId >>> 32));
		result = prime * result + (isCorrectData ? 1231 : 1237);
		result = prime * result + (isDiagnosisEstablished ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (patientId ^ (patientId >>> 32));
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
		DrugData other = (DrugData) obj;
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (appointmentTime == null) {
			if (other.appointmentTime != null) {
				return false;
			}
		} else if (!appointmentTime.equals(other.appointmentTime)) {
			return false;
		}
		if (drugId != other.drugId) {
			return false;
		}
		if (isCorrectData != other.isCorrectData) {
			return false;
		}
		if (isDiagnosisEstablished != other.isDiagnosisEstablished) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (patientId != other.patientId) {
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
		builder.append("DrugData [name=").append(name).append(", amount=")
				.append(amount).append(", appointmentTime=").append(appointmentTime)
				.append(", isCorrectData=").append(isCorrectData).append(", drugId=")
				.append(drugId).append(", patientId=").append(patientId)
				.append(", isDiagnosisEstablished=").append(isDiagnosisEstablished)
				.append("]");
		return builder.toString();
	}

}
