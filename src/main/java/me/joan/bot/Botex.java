package me.joan.bot;

import me.joan.Main;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.*;

import java.util.*;
import java.time.*;

public class Botex {
    public Main main;
    private JDA jda;

    private final String[] channel_names = {"-reglas"};
    private final String[] strings;
    private final ArrayList<TextChannel> channels;
    public static final String PREFIX = "x!"+" ";

    public Botex(Main main, String key, String[] strings) {
        this.main = main;
        this.channels = new ArrayList<TextChannel>();
        this.strings = strings;
    }
}
