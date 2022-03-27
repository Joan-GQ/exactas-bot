package me.joan.bot.command;

import me.joan.bot.Botex;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.Objects;

public class BotShutdownCommand implements IGuildMessageCommand, IGuildSlashCommand{
    private final Botex botex;

    public BotShutdownCommand(Botex botex) {
        this.botex = botex;
    }

    @Override
    public void handle(GuildMessageReceivedEvent event, List<String> args) {
        String adminID = "957664970320015360";
        String authorID = event.getMessage().getAuthor().getId();
        List<Role> authorRoles = event.getGuild().getMemberById(authorID).getRoles();
        Role authorRole = authorRoles
                .stream()
                .filter(role -> role.getId().equals(adminID)) // TODO: Load admin role ID from properties
                .findFirst()
                .orElse(null)
                ;
        boolean isAdmin = false;
        if(authorRole != null) isAdmin = true;

        if(isAdmin) {
            event.getMessage().delete().queue();
            try { event.getJDA().awaitReady();} catch(Exception ex) { ex.printStackTrace(); }
            System.out.println("[BotEx] Bot shut down via command.");
            botex.disableBot();
        } else {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("\u274C \u00A1Error!");
            embedBuilder.setColor(0x741f1f);
            embedBuilder.setDescription("Permisos insuficientes para realizar \u00E9sta acci\u00F3n.");
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }
    }

    @Override
    public void handle(SlashCommandEvent event) {

    }

    @Override
    public String getHelp() {
        return "Apaga el bot";
    }

    @Override
    public String getInvoke() {
        return "shutdown";
    }
}
