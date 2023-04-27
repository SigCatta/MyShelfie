package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.model.tiles.Color;

import java.util.HashMap;

public class Printer {

    static HashMap<Color, String> colorMap;
    public static final String NULL = ".";

    private static void enableCLIColors(boolean isColored) {
        colorMap = new HashMap<>();
        if (isColored) {
            // COLOR = ANSI_BACKGROUND_COLOR_ID + white spaces + ANSI_COLOR_RESET
            colorMap.put(Color.GREEN, "\033[0;102m" + " " + "\u001B[0m");
            colorMap.put(Color.YELLOW, "\033[0;103m" + " " + "\u001B[0m");
            colorMap.put(Color.BLUE, "\033[0;104m" + " " + "\u001B[0m");
            colorMap.put(Color.PINK, "\033[0;105m" + " " + "\u001B[0m");
            colorMap.put(Color.LIGHTBLUE, "\033[0;106m" + " " + "\u001B[0m");
            colorMap.put(Color.WHITE, "\033[0;107m" + " " + "\u001B[0m");
        } else {
            colorMap.put(Color.GREEN, "GG");
            colorMap.put(Color.YELLOW, "YY");
            colorMap.put(Color.BLUE, "BB");
            colorMap.put(Color.PINK, "PP");
            colorMap.put(Color.LIGHTBLUE, "LL");
            colorMap.put(Color.WHITE, "WW");
        }
        colorMap.put(Color.EMPTY, " ");
    }

    public static HashMap<Color, String> getColorMap(boolean isColored) {
        enableCLIColors(isColored);
        return colorMap;
    }
}
