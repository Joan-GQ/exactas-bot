package me.joan.bot.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.List;

public interface IGuildSlashCommand {
    void handle(SlashCommandEvent event);
    String getHelp();
    String getInvoke();
}