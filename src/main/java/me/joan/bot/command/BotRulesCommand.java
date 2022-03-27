package me.joan.bot.command;

import me.joan.bot.Botex;
import me.joan.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class BotRulesCommand implements IGuildMessageCommand, IGuildSlashCommand {
    @Override
    public void handle(GuildMessageReceivedEvent event, List<String> args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("\u270F Reglas");
        embedBuilder.setDescription
                ("""
                        \u25CF No spam
                        \u25CF Usar los canales de manera adecuada.
                        \u25CF Ejemplo: en #algebra no hablar de temas que ir\u00EDan en #analisis
                                """);
        embedBuilder.setColor(0x1f6974);
        event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
    }

    @Override
    public void handle(SlashCommandEvent event) {

    }

    @Override
    public String getHelp() {
        return "Muestra las reglas\n"+
                "Uso: `" + Botex.prefix + this.getInvoke() + "`";
    }

    @Override
    public String getInvoke() {
        return "reglas";
    }
}
