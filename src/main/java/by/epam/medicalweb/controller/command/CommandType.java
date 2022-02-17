package by.epam.medicalweb.controller.command;

import by.epam.medicalweb.controller.command.impl.*;

import static by.epam.medicalweb.model.entity.User.*;
import static by.epam.medicalweb.model.entity.User.UserRole.*;

import java.util.EnumSet;

public enum CommandType {

    INSERT_NEW_SERVICE(new InsertNewServiceCommand(), EnumSet.of(ADMIN)),
    EDIT_SERVICE(new EditServiceCommand(), EnumSet.of(ADMIN)),
    SHOW_ALL_SERVICES(new ShowAllServicesCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT, GUEST)),
    ADD_DOCTOR(new AddDoctorCommand(), EnumSet.of(ADMIN)),

    CHANGE_LOCALE(new ChangeLocaleCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT, GUEST)),
    REGISTER(new RegisterCommand(), EnumSet.of(ADMIN, GUEST)),
    LOG_IN(new LogInCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT, GUEST)),
    LOG_OUT(new LogOutCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT)),


    EDIT_PROFILE(new EditProfileCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT)),
    CHANGE_PASSWORD(new ChangePasswordCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT)),
    SHOW_ALL_USERS(new ShowAllUsersCommand(), EnumSet.of(ADMIN)),
    FIND_USER(new FindUserCommand(),EnumSet.of(ADMIN)),
    TOP_UP_BALANCE(new TopUpBalanceCommand(), EnumSet.of(PATIENT)),

    CREATE_VISIT(new CreateVisitCommand(),  EnumSet.of(PATIENT)),
    CANCEL_VISIT(new CancelVisitCommand(),  EnumSet.of(PATIENT, ADMIN)),
    SHOW_PATIENT_VISIT(new ShowPatientVisitCommand(),EnumSet.of(PATIENT)),
    SHOW_ALL_VISITS(new ShowAllVisitsCommand(), EnumSet.of(ADMIN)),

    SHOW_ALL_DOCTORS(new ShowAllDoctorsCommand(), EnumSet.of(PATIENT, GUEST,  ADMIN, DOCTOR)),
    SHOW_ALL_SPECIALIZATIONS(new ShowAllSpecializationsCommand(), EnumSet.of(PATIENT, GUEST, ADMIN, DOCTOR)),

    ADD_PRESCRIPTION(new AddPrescriptionCommand(), EnumSet.of(DOCTOR)),
    SHOW_PRESCRIPTION(new ShowPrescriptionCommand(), EnumSet.of(PATIENT)),

    UPLOAD_IMAGE(new UploadImageCommand(), EnumSet.of(ADMIN)),

    DEFAULT(new DefaultCommand(),EnumSet.of(PATIENT, GUEST,  ADMIN, DOCTOR));

    private final Command command;
    private EnumSet<UserRole> allowedRoles;

    CommandType(Command command, EnumSet<UserRole> allowedRoles) {
        this.command = command;
        this.allowedRoles = allowedRoles;
    }

    public Command getCommand() {
        return command;
    }

    public EnumSet<UserRole> getAllowedRoles() {
        return allowedRoles;
    }
}
