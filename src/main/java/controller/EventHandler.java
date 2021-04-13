package controller;

import command.*;
import error.NoSuchCommandException;
import error.NoSuchRoleException;
import model.RoleCommand;
import model.Server;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ui.DiscordTextUI;

import java.util.Optional;

public class EventHandler {
    private final Server server;
    public EventHandler(Server server) {
        this.server = server;
    }
    public void handle(MessageReceivedEvent event) {
            RoleCommand command = RoleCommand.parseRoleCommand(event);
            CommandHandler handler = switch (command.getType()) {
                case ADD -> new AddRoleCommandHandler();
                case REMOVE -> new RemoveRoleCommandHandler();
                case LIST -> new ListCommandHandler();
                case HELP -> new HelpCommandHandler();
                case UNKNOWN -> new UnknownCommandHandler();
            };
            handler.handle(command, server);
    }
}
