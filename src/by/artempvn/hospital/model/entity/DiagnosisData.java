package by.artempvn.hospital.model.entity;

/**
 * The Class DiagnosisData.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class DiagnosisData {
	private String name;
	private String date;
	private long patientId;
	private String establishedDoctor;
	private long purposeId;
	private boolean isCorrectData;
	private long diagnosisId;

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
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * Gets the established doctor.
	 *
	 * @return the established doctor
	 */
	public String getEstablishedDoctor() {
		return establishedDoctor;
	}

	/**
	 * Sets the established doctor.
	 *
	 * @param establishedDoctor the new established doctor
	 */
	public void setEstablishedDoctor(String establishedDoctor) {
		this.establishedDoctor = establishedDoctor;
	}

	/**
	 * Gets the purpose id.
	 *
	 * @return the purpose id
	 */
	public long getPurposeId() {
		return purposeId;
	}

	/**
	 * Sets the purpose id.
	 *
	 * @param purposeId the new purpose id
	 */
	public void setPurposeId(long purposeId) {
		this.purposeId = purposeId;
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
	 * Gets the diagnosis id.
	 *
	 * @return the diagnosis id
	 */
	public long getDiagnosisId() {
		return diagnosisId;
	}

	/**
	 * Sets the diagnosis id.
	 *
	 * @param diagnosisId the new diagnosis id
	 */
	public void setDiagnosisId(long diagnosisId) {
		this.diagnosisId = diagnosisId;
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (diagnosisId ^ (diagnosisId >>> 32));
		result = prime * result
				+ ((establishedDoctor == null) ? 0 : establishedDoctor.hashCode());
		result = prime * result + (isCorrectData ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (patientId ^ (patientId >>> 32));
		result = prime * result + (int) (purposeId ^ (purposeId >>> 32));
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
		DiagnosisData other = (DiagnosisData) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (diagnosisId != other.diagnosisId) {
			return false;
		}
		if (establishedDoctor == null) {
			if (other.establishedDoctor != null) {
				return false;
			}
		} else if (!establishedDoctor.equals(other.establishedDoctor)) {
			return false;
		}
		if (isCorrectData != other.isCorrectData) {
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
		if (purposeId != other.purposeId) {
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
		builder.append("DiagnosisData [name=").append(name).append(", date=")
				.append(date).append(", patientId=").append(patientId)
				.append(", establishedDoctor=").append(establishedDoctor)
				.append(", purposeId=").append(purposeId).append(", isCorrectData=")
				.append(isCorrectData).append(", diagnosisId=").append(diagnosisId)
				.append("]");
		return builder.toString();
	}

}
