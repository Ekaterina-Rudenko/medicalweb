package by.epam.medicalweb.controller;

import by.epam.medicalweb.controller.command.impl.*;

public enum CommandType {

    INSERT_NEW_SERVICE(new InsertNewServiceCommand()),
    EDIT_SERVICE(new EditServiceCommand()),
    SHOW_ALL_SERVICES(new ShowAllServicesCommand()),

    CHANGE_LOCALE(new ChangeLocaleCommand()),
    REGISTER(new RegisterCommand()),
    LOG_IN(new LogInCommand()),
    LOG_OUT(new LogOutCommand()),


    EDIT_PROFILE(new EditProfileCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    FIND_USER(new FindUserCommand()),
    TOP_UP_BALANCE(new TopUpBalanceCommand()),

    CREATE_VISIT(new CreateVisitCommand()),
    CANCEL_VISIT(new CancelVisitCommand()),
    SHOW_ALL_VISITS(new ShowAllVisitsCommand()),

    SHOW_ALL_DOCTORS(new ShowAllDoctorsCommand()),
    SHOW_ALL_SPECIALIZATIONS(new ShowAllSpecializationsCommand()),
    UPLOAD_PHOTO(new UploadPhotoCommand()),

    ADD_PRESCRIPTION(new AddPrescriptionCommand()),
    SHOW_PRESCRIPTION(new ShowPrescriptionCommand()),

    DEFAULT(new DefaultCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    }
