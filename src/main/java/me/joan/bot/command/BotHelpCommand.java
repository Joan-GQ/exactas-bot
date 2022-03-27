package me.joan.bot.command;

import me.joan.bot.Botex;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class BotHelpCommand implements IGuildMessageCommand, IGuildSlashCommand {
    private final CommandManager commandManager;

    public BotHelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    @Override
    public void handle(GuildMessageReceivedEvent event, List<String> args) {
        if (args.isEmpty()) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Todos mis comandos:");
            builder.setColor(0x1f6974);
            builder.setFooter("Para m\u00E1s info, ver: https://github.com/Joan-GQ/exactas-bot");

            StringBuilder descriptionBuilder = builder.getDescriptionBuilder();

            commandManager.getGuildMessageCommands().forEach(
                    (command) -> descriptionBuilder.append('`').append(command.getInvoke()).append("`\n")
            );

            builder.addField(
                    "Prefijo","`"+Botex.prefix+"`\n" +
                    "Ejemplo: `x! ping`",
                    false);

            event.getChannel().sendMessageEmbeds(builder.build()).queue();
            return;
        }

        String joined = String.join("", args);

        IGuildMessageCommand command = commandManager.getMessageCommand(joined);

        if (command == null) {
            event.getChannel().sendMessage("El comando `" + joined + "` no existe\n" +
                    "Usar `" + Botex.prefix + getInvoke() + "` para ver la lista entera de comandos").queue();
            return;
        }

        String message = "Ayuda para el comando `" + command.getInvoke() + ":`\n" + command.getHelp();

        event.getChannel().sendMessage(message).queue();
    }

    @Override
    public void handle(SlashCommandEvent event) {

    }

    @Override
    public String getHelp() {
        return "Muestra los comandos del bot.\n"+
                "Uso: `" + Botex.prefix + this.getInvoke() + "`";
    }

    @Override
    public String getInvoke() {
        return "ayuda";
    }
}
