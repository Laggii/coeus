package command;

import command.user.*;

/**
 * Created by Alexeev on 17.10.2016.
 */
public enum CommandProvider {
    VIEW_USERS("users", new ViewUsers()),
    VIEW_COURSES("courses", new ViewCourses()),
    VIEW_FRIENDS("friends", new ViewFriends()),
    VIEW_PROFILE("profile", new ViewProfile()),
    VIEW_SETTINGS("settings", new ViewSettings()),
    EDIT_PROFILE("editprofile", new EditProfile()),
    CHANGE_PASSWORD("changepassword", new ChangePassword());

    private final String name;

    private final Command command;

    CommandProvider(String name, Command command) {
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
