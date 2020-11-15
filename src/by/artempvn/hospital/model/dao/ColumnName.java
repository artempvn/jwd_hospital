package by.artempvn.hospital.model.dao;

/**
 * The Class ColumnName.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public class ColumnName {

	// roles
	public static final String ROLE_ID = "role_id";
	public static final String ROLE_TYPE = "role_type";

	// specialities
	public static final String SPECIALITY_ID = "speciality_id";
	public static final String SPECIALITY_TYPE = "speciality_type";

	// patients
	public static final String PATIENT_ID = "patient_id";
	public static final String PATIENT_NAME = "patient_name";
	public static final String PATIENT_SURNAME = "patient_surname";
	public static final String PATIENT_STATUS_ID_FK = "patient_status_id_fk";
	public static final String DIAGNOSIS_ID_FK = "diagnosis_id_fk";
	public static final String ADMISSION_DATE = "admission_date";
	public static final String PATIENT_BIRTHDAY = "patient_birthday";
	public static final String ADMISSION_DIAGNOSIS = "admission_diagnosis";
	public static final String TREATMENT_RESULT = "treatment_result";
	public static final String PATIENT_SECOND_NAME = "patient_second_name";
	public static final String DISCHARGE_DATE = "discharge_date";

	// patient_statuses
	public static final String PATIENT_STATUS_ID = "patient_status_id";
	public static final String PATIENT_STATUS_TYPE = "patient_status_type";

	// staff_statuses//
	public static final String STAFF_STATUS_ID = "staff_status_id";
	public static final String STAFF_STATUS_TYPE = "staff_status_type";

	// staff
	public static final String STAFF_ID = "staff_id";
	public static final String STAFF_LOGIN = "login";
	public static final String STAFF_PASSWORD = "password";
	public static final String STAFF_EMAIL = "email";
	public static final String STAFF_ROLE_ID = "role_id_fk";

	// medical_staff
	public static final String MEDICAL_STAFF_NAME = "staff_name";
	public static final String MEDICAL_STAFF_SURNAME = "staff_surname";
	public static final String MEDICAL_STAFF_SPECIALITY = "speciality_id_fk";
	public static final String MEDICAL_STAFF_SECOND_NAME = "staff_second_name";

	// procedure_types
	public static final String PROCEDURE_TYPE_ID = "procedure_type_id";
	public static final String PROCEDURE_TYPE = "procedure_type";

	// diagnosises
	public static final String DIAGNOSIS_NAME = "diagnosis_name";
	public static final String ESTABLISHED_BY_DOCTOR_ID_FK = "established_by_doctor_id_fk";
	public static final String DIAGNOSIS_DATE = "diagnosis_date";
	public static final String CHOSEN_PURPOSE_ID_FK = "chosen_purpose_id_fk";

	// purposes
	public static final String PURPOSE_ID = "purpose_id";
	public static final String ATTENDING_DOCTOR_ID_FK = "attending_doctor_id_fk";

	// drugs
	public static final String DRUG_ID = "drug_id";
	public static final String DRUG_NAME = "drug_name";
	public static final String AMOUNT = "amount";
	public static final String APPOINTMENT_TIME = "appointment_time";

	// procedures
	public static final String PROCEDURE_ID = "procedure_id";
	public static final String PROCEDURE_NAME = "procedure_name";
	public static final String DATE_START = "date_start";
	public static final String DATE_END = "date_end";
	public static final String PROCEDURE_TYPE_ID_FK = "procedure_type_id_fk";

	private ColumnName() {
	}

}
