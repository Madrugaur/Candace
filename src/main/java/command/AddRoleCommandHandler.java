package command;

import error.NoSuchRoleException;
import model.RoleCommand;
import model.Server;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import ui.DiscordTextUI;

import java.util.Optional;

public class AddRoleCommandHandler implements CommandHandler {
    @Override
    public void handle(RoleCommand addRoleCommand, Server server) {
        try {
            Member member = addRoleCommand.getSender();
            Optional<Role> roleOptional = server.getRole(addRoleCommand.getText());
            if (roleOptional.isEmpty()) throw new NoSuchRoleException(addRoleCommand.getText());
            Role role = roleOptional.get();
            addRoleCommand.getGuild().addRoleToMember(member, role).queue(
                    success -> DiscordTextUI.sendMessage(addRoleCommand.getChannel(), role.getName() + " added."),
                    fail -> DiscordTextUI.sendMessage(addRoleCommand.getChannel(), fail.getMessage())
            );

        } catch (NoSuchRoleException noSuchRoleException) {
            noSuchRoleException.printStackTrace();
            DiscordTextUI.sendMessage(addRoleCommand.getChannel(), noSuchRoleException.getMessage());
        } catch (HierarchyException hierarchyException) {
            hierarchyException.printStackTrace();
            DiscordTextUI.sendMessage(addRoleCommand.getChannel(), "I can't add the role, you're above me in the hierarchy!");
        }

    }
}
