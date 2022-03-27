package me.joan;

import me.joan.bot.Botex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger();
    public static Botex botex;

    public static void main(String[] args) {
        botex = new Botex();
        LOGGER.info("[BotEx] Starting!");
        LOGGER.info("[BotEx] Prefix is "+Botex.prefix);
    }
}
