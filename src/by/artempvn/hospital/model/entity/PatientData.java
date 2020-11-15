package by.artempvn.hospital.model.entity;

/**
 * The Class PatientData.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class PatientData {
	private static final String DEFAULT_STATUS = "registered";
	private long id;
	private String name;
	private String surname;
	private String secondName;
	private String status = DEFAULT_STATUS;
	private long diagnosisId;
	private String dateBirth;
	private String admissionDate;
	private String admissionDiagnosis;
	private boolean isAnyPurpose;
	private UserData attendingDoctor;
	private boolean isCorrectData;
	private String treatmentResult;
	private String dischargeDate;
	private boolean isDischarged;

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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
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
	 * Gets the date birth.
	 *
	 * @return the date birth
	 */
	public String getDateBirth() {
		return dateBirth;
	}

	/**
	 * Sets the date birth.
	 *
	 * @param dateBirth the new date birth
	 */
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}

	/**
	 * Checks if is any purpose.
	 *
	 * @return true, if is any purpose
	 */
	public boolean isAnyPurpose() {
		return isAnyPurpose;
	}

	/**
	 * Sets the any purpose.
	 *
	 * @param isAnyPurpose the new any purpose
	 */
	public void setAnyPurpose(boolean isAnyPurpose) {
		this.isAnyPurpose = isAnyPurpose;
	}

	/**
	 * Gets the attending doctor.
	 *
	 * @return the attending doctor
	 */
	public UserData getAttendingDoctor() {
		return attendingDoctor;
	}

	/**
	 * Sets the attending doctor.
	 *
	 * @param attendingDoctor the new attending doctor
	 */
	public void setAttendingDoctor(UserData attendingDoctor) {
		this.attendingDoctor = attendingDoctor;
	}

	/**
	 * Gets the admission date.
	 *
	 * @return the admission date
	 */
	public String getAdmissionDate() {
		return admissionDate;
	}

	/**
	 * Sets the admission date.
	 *
	 * @param admissionDate the new admission date
	 */
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * Gets the admission diagnosis.
	 *
	 * @return the admission diagnosis
	 */
	public String getAdmissionDiagnosis() {
		return admissionDiagnosis;
	}

	/**
	 * Sets the admission diagnosis.
	 *
	 * @param admissionDiagnosis the new admission diagnosis
	 */
	public void setAdmissionDiagnosis(String admissionDiagnosis) {
		this.admissionDiagnosis = admissionDiagnosis;
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
	 * Gets the treatment result.
	 *
	 * @return the treatment result
	 */
	public String getTreatmentResult() {
		return treatmentResult;
	}

	/**
	 * Sets the treatment result.
	 *
	 * @param treatmentResult the new treatment result
	 */
	public void setTreatmentResult(String treatmentResult) {
		this.treatmentResult = treatmentResult;
	}

	/**
	 * Gets the discharge date.
	 *
	 * @return the discharge date
	 */
	public String getDischargeDate() {
		return dischargeDate;
	}

	/**
	 * Sets the discharge date.
	 *
	 * @param dischargeDate the new discharge date
	 */
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	/**
	 * Checks if is discharged.
	 *
	 * @return true, if is discharged
	 */
	public boolean isDischarged() {
		return isDischarged;
	}

	/**
	 * Sets the discharged.
	 *
	 * @param isDischarged the new discharged
	 */
	public void setDischarged(boolean isDischarged) {
		this.isDischarged = isDischarged;
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
		result = prime * result
				+ ((admissionDate == null) ? 0 : admissionDate.hashCode());
		result = prime * result
				+ ((admissionDiagnosis == null) ? 0 : admissionDiagnosis.hashCode());
		result = prime * result
				+ ((attendingDoctor == null) ? 0 : attendingDoctor.hashCode());
		result = prime * result + ((dateBirth == null) ? 0 : dateBirth.hashCode());
		result = prime * result + (int) (diagnosisId ^ (diagnosisId >>> 32));
		result = prime * result
				+ ((dischargeDate == null) ? 0 : dischargeDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isAnyPurpose ? 1231 : 1237);
		result = prime * result + (isCorrectData ? 1231 : 1237);
		result = prime * result + (isDischarged ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result
				+ ((treatmentResult == null) ? 0 : treatmentResult.hashCode());
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
		PatientData other = (PatientData) obj;
		if (admissionDate == null) {
			if (other.admissionDate != null) {
				return false;
			}
		} else if (!admissionDate.equals(other.admissionDate)) {
			return false;
		}
		if (admissionDiagnosis == null) {
			if (other.admissionDiagnosis != null) {
				return false;
			}
		} else if (!admissionDiagnosis.equals(other.admissionDiagnosis)) {
			return false;
		}
		if (attendingDoctor == null) {
			if (other.attendingDoctor != null) {
				return false;
			}
		} else if (!attendingDoctor.equals(other.attendingDoctor)) {
			return false;
		}
		if (dateBirth == null) {
			if (other.dateBirth != null) {
				return false;
			}
		} else if (!dateBirth.equals(other.dateBirth)) {
			return false;
		}
		if (diagnosisId != other.diagnosisId) {
			return false;
		}
		if (dischargeDate == null) {
			if (other.dischargeDate != null) {
				return false;
			}
		} else if (!dischargeDate.equals(other.dischargeDate)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (isAnyPurpose != other.isAnyPurpose) {
			return false;
		}
		if (isCorrectData != other.isCorrectData) {
			return false;
		}
		if (isDischarged != other.isDischarged) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (secondName == null) {
			if (other.secondName != null) {
				return false;
			}
		} else if (!secondName.equals(other.secondName)) {
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
		if (treatmentResult == null) {
			if (other.treatmentResult != null) {
				return false;
			}
		} else if (!treatmentResult.equals(other.treatmentResult)) {
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
		builder.append("PatientData [id=").append(id).append(", name=").append(name)
				.append(", surname=").append(surname).append(", secondName=")
				.append(secondName).append(", status=").append(status)
				.append(", diagnosisId=").append(diagnosisId).append(", dateBirth=")
				.append(dateBirth).append(", admissionDate=").append(admissionDate)
				.append(", admissionDiagnosis=").append(admissionDiagnosis)
				.append(", isAnyPurpose=").append(isAnyPurpose)
				.append(", attendingDoctor=").append(attendingDoctor)
				.append(", isCorrectData=").append(isCorrectData)
				.append(", treatmentResult=").append(treatmentResult)
				.append(", dischargeDate=").append(dischargeDate)
				.append(", isDischarged=").append(isDischarged).append("]");
		return builder.toString();
	}

}
