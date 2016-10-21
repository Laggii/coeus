package command;

import exception.CommandException;

/**
 * Created by Alexeev on 17.10.2016.
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
            for (CommandEnum commandEnum : CommandEnum.values()) {
                if (name.equalsIgnoreCase(commandEnum.getName())) {
                    return commandEnum.getCommand();
                }
            }
        }
        throw new CommandException("Command not found");
    }
}
