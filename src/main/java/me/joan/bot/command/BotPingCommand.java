package me.joan.bot.command;

import me.joan.bot.Botex;
import me.joan.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class BotPingCommand implements IGuildMessageCommand, IGuildSlashCommand {
    @Override
    public void handle(GuildMessageReceivedEvent event, List<String> args) {
        event.getJDA().getRestPing().queue(time -> {
            long gatePing = event.getJDA().getGatewayPing();
            long restPing = time;

            String rest_emoji = Utils.pingToEmoji(restPing);
            String gate_emoji = Utils.pingToEmoji(gatePing);

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("\uD83E\uDD7E Pong!");
            embed.setDescription(rest_emoji+" REST ping: "+restPing+"ms\n\n" +
                    gate_emoji+" Gateway ping: "+gatePing+"ms");
            embed.setColor(Utils.pingToColor((restPing + gatePing) / 2L));

            event.getChannel().sendMessageEmbeds(embed.build()).queue();
        });
    }

    @Override
    public void handle(SlashCommandEvent event) {
        if(event.getName().equals("ping")) {
            long time = System.currentTimeMillis();
            event.reply("Pong!").setEphemeral(true) // reply or acknowledge
                    .flatMap(v ->
                            event.getHook().editOriginalFormat("Pong! - %d ms", System.currentTimeMillis() - time) // then edit original
                    ).queue(); // Queue both reply and edit
        }
    }

    @Override
    public String getHelp() {
        return "Pong!\n"+
                "Usage: `" + Botex.prefix + this.getInvoke() + "`";
    }

    @Override
    public String getInvoke() {
        return "ping";
    }
}
