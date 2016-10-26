package command;

import command.teacher.AddCourse;
import command.teacher.DeleteCourse;
import command.teacher.EditCourse;
import command.user.*;

/**
 * Created by Alexeev on 17.10.2016.
 */

/**
 * CommandProvider provides mapping for action request parameter with corresponding Command Object
 */
public enum CommandProvider {
    VIEW_USERS("users", new ViewUsers()),
    VIEW_COURSES("courses", new ViewCourses()),
    VIEW_COURSE("course", new ViewCourse()),
    VIEW_FRIENDS("friends", new ViewFriends()),
    VIEW_PROFILE("profile", new ViewProfile()),
    VIEW_SETTINGS("settings", new ViewSettings()),
    EDIT_PROFILE("editprofile", new EditProfile()),
    CHANGE_PASSWORD("changepassword", new ChangePassword()),
    ADD_FRIEND("addfriend", new AddFriend()),
    DEL_FRIEND("delfriend", new RemoveFriend()),
    JOIN_COURSE("joincourse", new JoinCourse()),
    LEFT_COURSE("leavecourse", new LeaveCourse()),
    ADD_COURSE("addcourse", new AddCourse()),
    EDIT_COURSE("editcourse", new EditCourse()),
    DEL_COURSE("delcourse", new DeleteCourse());

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
