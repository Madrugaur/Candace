package ui;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class DiscordTextUI {
    public static void sendMessage(TextChannel channel, String message) {
        channel.sendMessage(message).queue();
    }
    public static void sendMessage(TextChannel channel, MessageEmbed embed) {
        channel.sendMessage(embed).queue();
    }
}
