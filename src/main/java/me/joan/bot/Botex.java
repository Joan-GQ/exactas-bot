package me.joan.bot;

import me.joan.Main;
import me.joan.bot.command.CommandManager;
import me.joan.utils.PropertiesManager;

import me.joan.utils.Utils;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.*;
import java.time.*;

public class Botex {
    public static String prefix;
    private JDA jda;
    private BotexListener listener;
    private PropertiesManager propertiesManager;
    private CommandManager commandManager;

    private final String[] channelNames = {"-reglas"};
    private final ArrayList<TextChannel> channels = new ArrayList<>();

    public Botex() {
        this.propertiesManager = new PropertiesManager();
        this.propertiesManager.load(new File("botex.properties"));

        Botex.prefix = this.propertiesManager.getProperty("prefix");
        Botex.prefix = Utils.toUTF(Botex.prefix);

        this.commandManager = new CommandManager(this);
        this.listener = new BotexListener(this.commandManager);
        this.startBot(propertiesManager.getProperty("api"));
    }

    public void startBot(String key) {
        JDABuilder builder;
        builder = JDABuilder.createDefault(key);
        builder.setActivity(Activity.listening(Botex.prefix));
        builder.addEventListeners(this.listener);

        try {
            jda = builder.build();
            jda.upsertCommand("ping","Devuelve 'Pong!' junto con " +
                    "el tiempo en milisegundos que haya tardado en responder el bot").queue();
        } catch (LoginException logex) {
            logex.printStackTrace();
        }

        try {
            jda.awaitReady();
        } catch(InterruptedException intex) {
            intex.printStackTrace();
        }

        for(Guild g : this.jda.getGuilds()) {
            for (TextChannel t : g.getTextChannels()) {
                if (Utils.stringContainsItemFromList(t.getName(), channelNames)) {
                    channels.add(t);
                    //t.sendMessage(getBotString(STRING_ON)).queue(); // Activado
                }
            }
        }

        //Main.LOGGER.info(Collections.singletonList(channels));
        System.out.println("[BotEx] Starting!");
        System.out.println("[BotEx] Prefix is "+Botex.prefix);
    }

    public void disableBot() {
        this.jda.shutdown();
    }
}
