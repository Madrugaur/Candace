package command;

import model.RoleCommand;
import model.Server;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import ui.DiscordTextUI;

import javax.management.InvalidAttributeValueException;
import java.awt.*;
import java.util.List;

public class ListCommandHandler implements CommandHandler{
    private static final long NUMBER_DISPLAYABLE = 24;
    @Override
    public void handle(RoleCommand command, Server server) {
        String arg = command.getText();
        int pageNumber = 0;
        try {
            if (!arg.isBlank()) pageNumber = Integer.parseInt(arg) - 1;
            DiscordTextUI.sendMessage(command.getChannel(), generateRoleEmbed(server, pageNumber));
        } catch (NumberFormatException ignored) {
        } catch (InvalidAttributeValueException invalidAttributeValueException) {
            DiscordTextUI.sendMessage(command.getChannel(), invalidAttributeValueException.getMessage());
        }
    }

    private MessageEmbed generateRoleEmbed(Server server, int pageNumber) throws InvalidAttributeValueException {
        List<Role> roles = server.getRoles();
        int pageLimit = (int)Math.ceil(roles.size() / 24.0);
        if (pageNumber >= pageLimit)
            throw new InvalidAttributeValueException(
                    String.format("Can't go to page %d, there " +
                            (pageLimit == 1 ? "is only %d page!" : "are only %d pages!"), pageNumber + 1, pageLimit));

        EmbedBuilder builder = new EmbedBuilder();
        long skipping = NUMBER_DISPLAYABLE * pageNumber;
        builder.setColor(Color.ORANGE)
                .setTitle(String.format("%s Role List, Page %d of %d", server.getName(), pageNumber + 1, pageLimit));
        roles.stream().skip(skipping).limit(NUMBER_DISPLAYABLE).forEach(role -> builder.addField("", role.getName(), true));
        return builder.build();
    }
}
