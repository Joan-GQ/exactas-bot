package me.joan.bot.command;

import me.joan.Main;
import me.joan.bot.Botex;
import me.joan.bot.command.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.*;
import java.util.regex.Pattern;

public class CommandManager {

    private final Map<String, IGuildMessageCommand> guildMessageCommands = new HashMap<>();
    private final Map<String, IGuildSlashCommand> guildSlashCommands = new HashMap<>();
    Logger logger;

    public CommandManager() {
        addMessageCommand(new BotPingCommand());
        addSlashCommand(new BotPingCommand());
        this.logger = Main.LOGGER;
    }

    private void addMessageCommand(IGuildMessageCommand command) {
        if (!guildMessageCommands.containsKey(command.getInvoke())) {
            guildMessageCommands.put(command.getInvoke(), command);
        }
    }

    public Collection<IGuildMessageCommand> getIGuildMessageCommands() {
        return guildMessageCommands.values();
    }

    public IGuildMessageCommand getMessageCommand(@NotNull String name) {
        return guildMessageCommands.get(name);
    }

    private void addSlashCommand(IGuildSlashCommand command) {
        if (!guildSlashCommands.containsKey(command.getInvoke())) {
            guildSlashCommands.put(command.getInvoke(), command);
        }
    }

    public Collection<IGuildSlashCommand> getSlashCommands() {
        return guildSlashCommands.values();
    }

    public IGuildSlashCommand getSlashCommand(@NotNull String name) {
        return guildSlashCommands.get(name);
    }

    void handleCommand(Event event) {
        if(event.getClass() == GuildMessageReceivedEvent.class) {
            handleMessageCommand((GuildMessageReceivedEvent) event);
        } else if(event.getClass() == SlashCommandEvent.class ) {
            handleSlashCommand((SlashCommandEvent) event);
        }
    }

    public void handleMessageCommand(GuildMessageReceivedEvent event) {
        User author = event.getAuthor();
        GuildChannel channel = event.getChannel();
        Guild guild = event.getGuild();


        final String[] split = event.getMessage().getContentRaw().replaceFirst(
                "(?i)" + Pattern.quote(Botex.prefix), "").split("\\s+");
        final String invoke = split[0].toLowerCase();

        if (guildMessageCommands.containsKey(invoke)) {
            final List<String> args = Arrays.asList(split).subList(1, split.length);

            event.getChannel().sendTyping().queue();
            guildMessageCommands.get(invoke).handle(event, args);
        } else {
            if(logger != null)
                System.out.println("[BotEx] "+guild.getName()+":"+channel.getName()+"."+author.getName());
        }
    }

    public void handleSlashCommand(SlashCommandEvent event) {
        String commandName = event.getName();
        if(guildSlashCommands.containsKey(commandName)) {
            guildSlashCommands.get(commandName).handle(event);
        } else {
            if(logger != null)
                System.out.println("[BotEx] Unknown slash command "+commandName);
        }
    }
}