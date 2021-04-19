package info;

import model.RoleCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandInfo {
    public static final String[][] commands;
    static {
        commands = new String[][] {
                { RoleCommand.COMMAND_PREFIX + "help, h", "Displays this menu, which shows all of my commands and their usage." },
                { RoleCommand.COMMAND_PREFIX + "add <role name>, a <role name>", "Adds a role to your account, if it exists." },
                { RoleCommand.COMMAND_PREFIX + "remove <role name>, r <role name>", "Removes a role from your account, if you have it." },
                { RoleCommand.COMMAND_PREFIX + "list [page number], l [page number]", "List all of the roles for this server." }
        };
    }
}
