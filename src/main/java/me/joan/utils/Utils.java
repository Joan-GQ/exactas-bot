package me.joan.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Utils {

    /* ------------------------------------------------------------------------------------ */

    private static final DecimalFormat TIME_FORMATTER = new DecimalFormat("########0.000");

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }

    public static String toUTF(String string) {
        return new String(string.getBytes(), StandardCharsets.UTF_8);
    }

    /* ------------------------------------------------------------------------------------ */

    public enum Color {
        RED(0xDE2E43),
        ORANGE(0xF5900C),
        GREEN(0x79B15A);

        private final int color;

        Color(int color) {
            this.color = color;
        }

        public static int getColor(Color element) {
            return element.color;
        }
    }

    /* ------------------------------------------------------------------------------------ */

    public static String pingToEmoji(long ping, int min, int max) {
        String emoji = "";

        if (ping >= max) { emoji = "\uD83D\uDD34"; }
        else if(ping <= min) { emoji = "\uD83D\uDFE2"; }
        else { emoji = "\uD83D\uDFE1"; }

        return emoji;
    }

    public static String pingToEmoji(long ping) {
        return pingToEmoji(ping, 400, 700);
    }

    public static String pingToEmoji(String formattedPing, int min, int max) {
        if(formattedPing.contains("*")) formattedPing = formattedPing.replace("*","");
        return pingToEmoji(Long.parseLong(formattedPing), min, max);
    }

    public static String pingToEmoji(String formattedPing) {
        if(formattedPing.contains("*")) formattedPing = formattedPing.replace("*","");
        return pingToEmoji(Long.parseLong(formattedPing));
    }

    /* ------------------------------------------------------------------------------------ */

    public static int pingToColor(long ping, int min, int max) {
        int color = 0x000000;

        /* #79b15a #f5900c #de2e43 */

        if (ping >= max) { color = 0xDE2E43; }
        else if(ping <= min) { color = 0x79B15A; }
        else { color = 0xF5900C; }

        return color;
    }

    public static int pingToColor(long ping) {
        return pingToColor(ping, 400, 700);
    }

    public static int pingToColor(String formattedPing, int min, int max) {
        if(formattedPing.contains("*")) formattedPing = formattedPing.replace("*","");
        return pingToColor(Long.parseLong(formattedPing), min, max);
    }

    public static int pingToColor(String formattedPing) {
        if(formattedPing.contains("*")) formattedPing = formattedPing.replace("*","");
        return pingToColor(Long.parseLong(formattedPing));
    }

    /* ------------------------------------------------------------------------------------ */
}
