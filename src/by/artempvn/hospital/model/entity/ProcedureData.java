package by.artempvn.hospital.model.entity;

/**
 * The Class ProcedureData.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ProcedureData {
	private String name;
	private String dateStart;
	private String dateEnd;
	private String type;
	private boolean isCorrectData;
	private long procedureId;
	private long patientId;
	private boolean isDiagnosisEstablished = true;
	private boolean isDoctorRemoved;

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
	 * Gets the date start.
	 *
	 * @return the date start
	 */
	public String getDateStart() {
		return dateStart;
	}

	/**
	 * Sets the date start.
	 *
	 * @param dateStart the new date start
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * Gets the date end.
	 *
	 * @return the date end
	 */
	public String getDateEnd() {
		return dateEnd;
	}

	/**
	 * Sets the date end.
	 *
	 * @param dateEnd the new date end
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * Gets the procedure id.
	 *
	 * @return the procedure id
	 */
	public long getProcedureId() {
		return procedureId;
	}

	/**
	 * Sets the procedure id.
	 *
	 * @param procedureId the new procedure id
	 */
	public void setProcedureId(long procedureId) {
		this.procedureId = procedureId;
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
	 * Checks if is doctor removed.
	 *
	 * @return true, if is doctor removed
	 */
	public boolean isDoctorRemoved() {
		return isDoctorRemoved;
	}

	/**
	 * Sets the doctor removed.
	 *
	 * @param isDoctorRemoved the new doctor removed
	 */
	public void setDoctorRemoved(boolean isDoctorRemoved) {
		this.isDoctorRemoved = isDoctorRemoved;
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
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		result = prime * result + (isCorrectData ? 1231 : 1237);
		result = prime * result + (isDiagnosisEstablished ? 1231 : 1237);
		result = prime * result + (isDoctorRemoved ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (patientId ^ (patientId >>> 32));
		result = prime * result + (int) (procedureId ^ (procedureId >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ProcedureData other = (ProcedureData) obj;
		if (dateEnd == null) {
			if (other.dateEnd != null) {
				return false;
			}
		} else if (!dateEnd.equals(other.dateEnd)) {
			return false;
		}
		if (dateStart == null) {
			if (other.dateStart != null) {
				return false;
			}
		} else if (!dateStart.equals(other.dateStart)) {
			return false;
		}
		if (isCorrectData != other.isCorrectData) {
			return false;
		}
		if (isDiagnosisEstablished != other.isDiagnosisEstablished) {
			return false;
		}
		if (isDoctorRemoved != other.isDoctorRemoved) {
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
		if (procedureId != other.procedureId) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
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
		builder.append("ProcedureData [name=").append(name).append(", dateStart=")
				.append(dateStart).append(", dateEnd=").append(dateEnd)
				.append(", type=").append(type).append(", isCorrectData=")
				.append(isCorrectData).append(", procedureId=").append(procedureId)
				.append(", patientId=").append(patientId)
				.append(", isDiagnosisEstablished=").append(isDiagnosisEstablished)
				.append(", isDoctorRemoved=").append(isDoctorRemoved).append("]");
		return builder.toString();
	}

}
