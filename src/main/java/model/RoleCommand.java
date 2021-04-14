package model;

import error.NoSuchCommandException;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Locale;

public class RoleCommand {
    public static final String COMMAND_PREFIX = ".";

    public enum Type { ADD, REMOVE, LIST, HELP, UNKNOWN }

    public final Guild guild;
    public final Type type;
    public final String text;
    public final Member sender;
    public final TextChannel channel;
    private RoleCommand(Guild guild, Type type, String text, Member sender, TextChannel textChannel) {
        this.guild = guild;
        this.type = type;
        this.text = text;
        this.sender = sender;
        this.channel = textChannel;
    }

    public Guild getGuild() {
        return guild;
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public Member getSender() {
        return sender;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public static RoleCommand parseRoleCommand(MessageReceivedEvent event){
        String raw = event.getMessage().getContentRaw();
        Type type = getType(raw);
        String request = parseRequest(raw);
        return new RoleCommand(event.getGuild(), type, request, event.getMember(), event.getTextChannel());
    }

    private static String parseRequest(String command) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(command.split(" ")).skip(1).forEach( part -> builder.append(part).append(" "));
        return builder.toString().trim();
    }

    private static Type getType(String command) {
        String firstWord = command.replaceFirst(COMMAND_PREFIX, "").split(" ")[0].toLowerCase(Locale.ROOT);
        return switch (firstWord) {
            case "add" -> Type.ADD;
            case "remove" -> Type.REMOVE;
            case "list" -> Type.LIST;
            case "help" -> Type.HELP;
            default -> Type.UNKNOWN;
        };
    }
}

