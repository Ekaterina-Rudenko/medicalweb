package by.epam.medicalweb.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static Logger logger = LogManager.getLogger();

    public static Command defineCommandType(String command) {
        Command definedCommand;
        if (command == null || command.isEmpty()) {
            return CommandType.DEFAULT.getCommand();
        }
        try{
            definedCommand = CommandType.valueOf(command.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e){
            logger.log(Level.ERROR, "Error while defining command type ", e);
            definedCommand = CommandType.DEFAULT.getCommand();
        }
        return definedCommand;
    }

}
