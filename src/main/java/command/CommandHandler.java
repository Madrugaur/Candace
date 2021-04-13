package command;

import model.RoleCommand;
import model.Server;

public interface CommandHandler {
    public void handle(RoleCommand command, Server server);
}
