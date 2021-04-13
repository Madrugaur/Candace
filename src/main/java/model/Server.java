package model;

import controller.EventHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Server {
    private final Guild guild;
    private final HashMap<String, Role> roleMap;
    private final EventHandler eventHandler;
    public Server(Guild guild) {
        this.guild = guild;
        this.roleMap = new HashMap<>();
        this.eventHandler = new EventHandler(this);
        initRoleMap();
    }

    private void initRoleMap() {
        List<Role> roles = guild.getRoles();
        roles.forEach(role -> roleMap.put(role.getName(), role));
    }

    public String getName() {
        return guild.getName();
    }

    public Optional<Role> getRole(String name) {
        if (roleMap.containsKey(name)) {
            return Optional.of(roleMap.get(name));
        } else {
            return Optional.empty();
        }
    }

    public List<Role> getRoles() {
        return guild.getRoles();
    }

    public void handle(MessageReceivedEvent event) {
        this.eventHandler.handle(event);
    }
}
