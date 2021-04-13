package controller;
import model.RoleCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.Objects;

public class DiscordController {
    private JDA jda;
    private Logger log = LoggerFactory.getLogger(DiscordController.class);
    public DiscordController() {
        try {
            ServerController serverController = new ServerController();
            DiscordEventListener discordEventListener = new DiscordEventListener(serverController);
            jda = Objects.requireNonNull(startJDA(discordEventListener));
            serverController.initServerMap(jda);
            jda.getPresence().setActivity(Activity.listening(RoleCommand.COMMAND_PREFIX + "help"));
            log.info("Candace started");
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    private JDA startJDA(DiscordEventListener listener) throws LoginException {
        String token = System.getenv("CANDACE_TOKEN");
        if (token == null) {
            log.error("startJDA() -> Bot Token CANNOT be null! Check the environmental variables to make sure " +
                    "'CANDACE_TOKEN' exists. If yes, make sure an active token is being used!");
            return null;
        } else {
            return JDABuilder.createDefault(token)
                    .addEventListeners(listener)
                    .enableIntents(Arrays.asList(GatewayIntent.valueOf("GUILD_PRESENCES")))
                    .build();
        }
    }

    public static void main(String[] args) {
        new DiscordController();
    }

}
