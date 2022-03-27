package me.joan.bot.command;

import me.joan.bot.Botex;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class BotResourcesCommand implements IGuildMessageCommand, IGuildSlashCommand {

    /* TODO: It's probably better to make:
        a) Customizable Embeds from commands (via Message or Slash)
        b) Customizable Embeds from a file with specific format.
     */
    @Override
    public void handle(GuildMessageReceivedEvent event, List<String> args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("\uD83D\uDCD6 Recursos");
        embedBuilder.setColor(0x1f6974);
        embedBuilder.addField(new MessageEmbed.Field(
                "Repositorio del grupo",
                "https://github.com/Joan-GQ/exactas",
                false
        ));
        embedBuilder.addField(new MessageEmbed.Field(
                "CUBA Wiki",
                "https://www.cubawiki.com.ar",
                false
        ));
        event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
    }

    @Override
    public void handle(SlashCommandEvent event) {

    }

    @Override
    public String getHelp() {
        return "Muestra los recursos\n"+
                "Uso: `" + Botex.prefix + this.getInvoke() + "`";
    }

    @Override
    public String getInvoke() {
        return "recursos";
    }
}
