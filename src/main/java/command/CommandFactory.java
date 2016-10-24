package command;

import exception.CommandException;

/**
 * Created by Alexeev on 17.10.2016.
 */

/**
 * CommandFactory provides suitable Command object based on user "action" request parameter
 */
public class CommandFactory {
    private static CommandFactory ourInstance = new CommandFactory();

    public static CommandFactory getInstance() {
        return ourInstance;
    }

    private CommandFactory() {
    }

    public Command getCommand(String name) throws CommandException {
        if (name != null) {
            for (CommandProvider commandProvider : CommandProvider.values()) {
                if (name.equalsIgnoreCase(commandProvider.getName())) {
                    return commandProvider.getCommand();
                }
            }
        }
        throw new CommandException("Command not found: " + name);
    }
}
