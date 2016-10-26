package command;

import exception.CommandException;

/**
 * Created by Alexeev on 17.10.2016.
 */

/**
 * CommandFactory provides suitable Command object based on user "action" request parameter
 * Implementation of the FactoryMethod pattern
 */
public class CommandFactory {
    private static CommandFactory ourInstance = new CommandFactory();

    public static CommandFactory getInstance() {
        return ourInstance;
    }

    private CommandFactory() {
    }

    /**
     * Get Command object from CommandProvider by command request parameter
     *
     * @param name of the command from request
     * @return Command object
     * @throws CommandException
     */
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
