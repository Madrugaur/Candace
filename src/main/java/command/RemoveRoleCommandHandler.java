package command;

import error.NoSuchCommandException;
import error.NoSuchRoleException;
import model.RoleCommand;
import model.Server;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import ui.DiscordTextUI;

import java.util.Optional;

public class RemoveRoleCommandHandler implements CommandHandler{
    @Override
    public void handle(RoleCommand command, Server server) {
        try {
            Member member = command.getSender();
            Optional<Role> roleOptional = server.getRole(command.getText());
            if (roleOptional.isEmpty()) throw new NoSuchRoleException(command.getText());
            command.getGuild().removeRoleFromMember(member, roleOptional.get()).queue();
        } catch (NoSuchRoleException noSuchRoleException) {
            noSuchRoleException.printStackTrace();
            DiscordTextUI.sendMessage(command.getChannel(), noSuchRoleException.getMessage());
        }

    }
}
