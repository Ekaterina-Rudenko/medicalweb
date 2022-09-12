package by.epam.medicalweb.controller.command;

import by.epam.medicalweb.controller.command.impl.*;

import static by.epam.medicalweb.model.entity.User.*;
import static by.epam.medicalweb.model.entity.User.UserRole.*;

import by.epam.medicalweb.model.entity.Patient;
import java.util.EnumSet;

public enum CommandType {

  EDIT_SERVICE(new EditServiceCommand(), EnumSet.of(ADMIN)),
  SHOW_ALL_SERVICES(new ShowAllServicesCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT, GUEST)),
  FIND_SERVICES_BY_SPECIALIZATION(new FindServicesBySpecializationCommand(),
      EnumSet.of(PATIENT, GUEST, DOCTOR, ADMIN)),
  FIND_DOCTORS_BY_SPECIALIZATION(new FindDoctorsBySpecializationCommand(),
      EnumSet.of(PATIENT, GUEST, DOCTOR, ADMIN)),
  ADD_DOCTOR(new AddDoctorCommand(), EnumSet.of(ADMIN)),

  CHANGE_LOCALE(new ChangeLocaleCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT, GUEST)),
  REGISTER(new RegisterCommand(), EnumSet.of(ADMIN, GUEST)),
  LOG_IN(new LogInCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT, GUEST)),
  LOG_OUT(new LogOutCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT)),


  EDIT_PROFILE(new EditProfileCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT)),
  CHANGE_PASSWORD(new ChangePasswordCommand(), EnumSet.of(ADMIN, DOCTOR, PATIENT)),
  TOP_UP_BALANCE(new TopUpBalanceCommand(), EnumSet.of(PATIENT)),

  CREATE_VISIT(new CreateVisitCommand(), EnumSet.of(PATIENT)),
  CANCEL_VISIT(new CancelVisitCommand(), EnumSet.of(PATIENT, ADMIN)),

  SHOW_ALL_DOCTORS(new ShowAllDoctorsCommand(), EnumSet.of(PATIENT, GUEST, ADMIN, DOCTOR)),
  SHOW_ALL_SPECIALIZATIONS(new ShowAllSpecializationsCommand(),
      EnumSet.of(PATIENT, GUEST, ADMIN, DOCTOR)),

  UPLOAD_IMAGE(new UploadImageCommand(), EnumSet.of(ADMIN)),

  DEFAULT(new DefaultCommand(), EnumSet.of(PATIENT, GUEST, ADMIN, DOCTOR)),

  FIND_SPECIALIZATION_BY_ID(new FindSpecializationByIdCommand(),
      EnumSet.of(PATIENT, GUEST, ADMIN, DOCTOR)),
  FIND_TIME_SLOTS_BY_DOCTOR_AND_DATE(new FindTimeSlotsByDoctorAndDateCommand(),
      EnumSet.of(PATIENT, ADMIN, DOCTOR)),
  FIND_VISITS_BY_PATIENT_ID(new FindVisitsByPatientIdCommand(), EnumSet.of(PATIENT, ADMIN, DOCTOR)),
  ADD_SERVICE(new AddServiceCommand(),EnumSet.of(ADMIN)),
  ADD_SPECIALIZATION(new AddSpecializationCommand(), EnumSet.of(ADMIN));

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
