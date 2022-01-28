package by.epam.medicalweb.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static Logger logger = LogManager.getLogger();

    public static Command defineCommandType(String command) {
        Command definedCommand = CommandType.DEFAULT.getCommand();
        try{
            if (command != null) {
                CommandType commandType = CommandType.valueOf(command.toUpperCase());
                definedCommand = commandType.getCommand();
            }
        } catch (IllegalArgumentException e){
            logger.log(Level.ERROR, "Error while defining command type ", e);
        }
        return definedCommand;
    }
}
