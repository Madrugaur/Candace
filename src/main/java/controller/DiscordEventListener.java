package controller;

import error.NoSuchCommandException;
import model.RoleCommand;
import model.Server;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;

public class DiscordEventListener extends ListenerAdapter {
    private final ServerController serverController;
    public DiscordEventListener(ServerController serverController) {
        this.serverController = serverController;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getChannelType().equals(ChannelType.PRIVATE)) return;
        if (!event.getMessage().getContentRaw().startsWith(RoleCommand.COMMAND_PREFIX)) return;
        passToHandler(event);
    }

    private void passToHandler(MessageReceivedEvent event) {
        serverController.handle(event);
    }
}
