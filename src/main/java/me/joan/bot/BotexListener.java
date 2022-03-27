package me.joan.bot;

import me.joan.Main;
import me.joan.bot.command.CommandManager;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.*;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class BotexListener extends ListenerAdapter {
    private final CommandManager commandManager;
    private final Logger LOGGER;

    public BotexListener(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.LOGGER = Main.LOGGER;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);
        this.LOGGER.info(String.format("[Botyta] Logged as %#s", event.getJDA().getSelfUser()));
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        User author = event.getAuthor();
        Message message = event.getMessage();

        if(author.isBot() || event.isWebhookMessage()) return;

        String content = message.getContentDisplay();
        String messageRaw = message.getContentRaw();

        if(messageRaw.startsWith(Botex.prefix)) {
            this.LOGGER.info("[Botyta] Discord issued server command "+ messageRaw);
            commandManager.handleMessageCommand(event);
        } else {

        }
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        super.onSlashCommand(event);
        commandManager.handleSlashCommand(event);
    }
}
