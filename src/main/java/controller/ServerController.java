package controller;

import error.NoSuchCommandException;
import model.RoleCommand;
import model.Server;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;

public class ServerController {
    private final HashMap<Guild, Server> serverMap;
    public ServerController() {
        this.serverMap = new HashMap<>();
    }

    public void initServerMap(JDA jda) {
        System.out.println(jda.getGuilds().size());
        jda.getGuilds().forEach(guild -> serverMap.put(guild, new Server(guild)));
    }

    public void handle(MessageReceivedEvent event) {
        Guild key = event.getGuild();
        if (!serverMap.containsKey(key)) serverMap.put(key, new Server(key));
        serverMap.get(key).handle(event);
    }
}
