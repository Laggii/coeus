package command;

import command.user.ViewCourses;
import command.user.ViewProfile;
import command.user.ViewSettings;
import command.user.ViewUsers;

/**
 * Created by Alexeev on 17.10.2016.
 */
public enum CommandEnum {
    VIEW_USERS("users", new ViewUsers()),
    VIEW_COURSES("courses", new ViewCourses()),
    VIEW_PROFILE("profile", new ViewProfile()),
    VIEW_SETTINGS("settings", new ViewSettings());

    private final String name;

    private final Command command;

    CommandEnum(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public Command getCommand() {
        return command;
    }
}
