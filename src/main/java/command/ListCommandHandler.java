package command;

import model.RoleCommand;
import model.Server;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import ui.DiscordTextUI;

import java.awt.*;
import java.util.List;

public class ListCommandHandler implements CommandHandler{
    @Override
    public void handle(RoleCommand command, Server server) {
        DiscordTextUI.sendMessage(command.getChannel(), generateRoleEmbed(server));
    }

    private MessageEmbed generateRoleEmbed(Server server) {
        List<Role> roles = server.getRoles();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.BLUE)
                .setTitle(String.format("%s Role List", server.getName()));
        roles.forEach(role -> builder.addField("", role.getName(), true));
        return builder.build();
    }
}
