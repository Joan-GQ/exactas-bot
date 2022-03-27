package me.joan;

import me.joan.bot.Botex;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Main {
    public static Logger LOGGER;
    public static Botex botex;

    public static void main(String[] args) {
        LOGGER = LoggerFactory.getLogger(Main.class);
        botex = new Botex();
    }
}
