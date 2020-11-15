package by.artempvn.hospital.model.entity;

/**
 * The Class PurposeData.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class PurposeData {
	private boolean isSucceed;
	private boolean isDiagnosisEstablished = true;

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
	 * Checks if is succeed.
	 *
	 * @return true, if is succeed
	 */
	public boolean isSucceed() {
		return isSucceed;
	}

	/**
	 * Sets the succeed.
	 *
	 * @param isSucceed the new succeed
	 */
	public void setSucceed(boolean isSucceed) {
		this.isSucceed = isSucceed;
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
		result = prime * result + (isDiagnosisEstablished ? 1231 : 1237);
		result = prime * result + (isSucceed ? 1231 : 1237);
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
		PurposeData other = (PurposeData) obj;
		if (isDiagnosisEstablished != other.isDiagnosisEstablished) {
			return false;
		}
		if (isSucceed != other.isSucceed) {
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
		builder.append("PurposeData [isSucceed=").append(isSucceed)
				.append(", isDiagnosisEstablished=").append(isDiagnosisEstablished)
				.append("]");
		return builder.toString();
	}

}
