package command;

import info.CommandInfo;
import model.RoleCommand;
import model.Server;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import ui.DiscordTextUI;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class HelpCommandHandler implements CommandHandler {
    @Override
    public void handle(RoleCommand command, Server server) {
        DiscordTextUI.sendMessage(command.getChannel(), generateHelpEmbed());
    }

    private MessageEmbed generateHelpEmbed() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Candace Help Menu")
                .setColor(Color.ORANGE)
                .setDescription("A menu showing all of my commands.");
        Arrays.stream(CommandInfo.commands).forEach(command -> builder.addField(command[0], command[1], false));
        return builder.build();
    }
}
