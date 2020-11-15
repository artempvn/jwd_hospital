package by.artempvn.hospital.controller.command;

import by.artempvn.hospital.controller.command.impl.ActivateUserCommand;
import by.artempvn.hospital.controller.command.impl.AddAttendingDoctorCommand;
import by.artempvn.hospital.controller.command.impl.AddDrugCommand;
import by.artempvn.hospital.controller.command.impl.AddProcedureCommand;
import by.artempvn.hospital.controller.command.impl.AddPurposeCommand;
import by.artempvn.hospital.controller.command.impl.BackToPreviousCommand;
import by.artempvn.hospital.controller.command.impl.BanUserCommand;
import by.artempvn.hospital.controller.command.impl.CreatePatientCommand;
import by.artempvn.hospital.controller.command.impl.CreateUserCommand;
import by.artempvn.hospital.controller.command.impl.DeleteDrugPurposeCommand;
import by.artempvn.hospital.controller.command.impl.DeleteProcedurePurposeCommand;
import by.artempvn.hospital.controller.command.impl.DischargePatientCommand;
import by.artempvn.hospital.controller.command.impl.EstablishDiagnosisCommand;
import by.artempvn.hospital.controller.command.impl.LoginCommand;
import by.artempvn.hospital.controller.command.impl.LogoutCommand;
import by.artempvn.hospital.controller.command.impl.MainCommand;
import by.artempvn.hospital.controller.command.impl.SetLocaleEnCommand;
import by.artempvn.hospital.controller.command.impl.SetLocaleRuCommand;
import by.artempvn.hospital.controller.command.impl.ShowDiagnosisCommand;
import by.artempvn.hospital.controller.command.impl.ShowDoctorCommand;
import by.artempvn.hospital.controller.command.impl.ShowPatientCommand;
import by.artempvn.hospital.controller.command.impl.ShowPatientPurposeCommand;
import by.artempvn.hospital.controller.command.impl.ShowPatientsCommand;
import by.artempvn.hospital.controller.command.impl.ShowStaffCommand;
import by.artempvn.hospital.controller.command.impl.ShowStaffPurposeCommand;
import by.artempvn.hospital.controller.command.impl.UnbanUserCommand;
import by.artempvn.hospital.controller.command.impl.UpdateDiagnosisCommand;

/**
 * The Enum CommandType.
 * 
 * @author Artem Piven
 * @version 1.0
 */
public enum CommandType {

	LOGIN(new LoginCommand()), MAIN(new MainCommand()),
	LOGOUT(new LogoutCommand()), CREATE_USER(new CreateUserCommand()),
	SHOW_STAFF(new ShowStaffCommand()), SET_LOCALE_RU(new SetLocaleRuCommand()),
	SET_LOCALE_EN(new SetLocaleEnCommand()),
	ACTIVATE_USER(new ActivateUserCommand()),
	CREATE_PATIENT(new CreatePatientCommand()),
	SHOW_PATIENTS(new ShowPatientsCommand()),
	ESTABLISH_DIAGNOSIS(new EstablishDiagnosisCommand()),
	ADD_ATTENDING_DOCTOR(new AddAttendingDoctorCommand()),
	ADD_PROCEDURE(new AddProcedureCommand()), ADD_DRUG(new AddDrugCommand()),
	ADD_PURPOSE(new AddPurposeCommand()),
	DISCHARGE_PATIENT(new DischargePatientCommand()),
	SHOW_DIAGNOSIS(new ShowDiagnosisCommand()),
	SHOW_PATIENT_PURPOSE(new ShowPatientPurposeCommand()),
	SHOW_STAFF_PURPOSE(new ShowStaffPurposeCommand()),
	BAN_USER(new BanUserCommand()), UNBAN_USER(new UnbanUserCommand()),
	BACK_TO_PREVIOUS(new BackToPreviousCommand()),
	UPDATE_DIAGNOSIS(new UpdateDiagnosisCommand()),
	DELETE_DRUG(new DeleteDrugPurposeCommand()),
	DELETE_PROCEDURE(new DeleteProcedurePurposeCommand()),
	SHOW_PATIENT(new ShowPatientCommand()), SHOW_DOCTOR(new ShowDoctorCommand());

	private Command command;

	CommandType(Command command) {
		this.command = command;
	}

	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}

}
