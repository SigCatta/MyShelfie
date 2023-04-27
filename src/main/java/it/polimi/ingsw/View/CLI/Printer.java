package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.virtualModel.Shelf;

import java.util.ArrayList;
import java.util.HashMap;

public class Printer {

    private Color[][] mainShelf;
    static HashMap<Color, String> colorMap;
    public static final String NULL = "..";

    public Printer(boolean isColored) {
        this.mainShelf = new Shelf().getShelf();
        enableCLIColors(isColored);
    }

    private static void enableCLIColors(boolean isColored) {
        colorMap = new HashMap<>();
        if (isColored) {
            // COLOR = ANSI_BACKGROUND_COLOR_ID + white spaces + ANSI_COLOR_RESET
            colorMap.put(Color.GREEN, "\033[0;102m" + "  " + "\u001B[0m");
            colorMap.put(Color.YELLOW, "\033[0;103m" + "  " + "\u001B[0m");
            colorMap.put(Color.BLUE, "\033[0;104m" + "  " + "\u001B[0m");
            colorMap.put(Color.PINK, "\033[0;105m" + "  " + "\u001B[0m");
            colorMap.put(Color.LIGHTBLUE, "\033[0;106m" + "  " + "\u001B[0m");
            colorMap.put(Color.WHITE, "\033[0;107m" + "  " + "\u001B[0m");
        } else {
            colorMap.put(Color.GREEN, "GG");
            colorMap.put(Color.YELLOW, "YY");
            colorMap.put(Color.BLUE, "BB");
            colorMap.put(Color.PINK, "PP");
            colorMap.put(Color.LIGHTBLUE, "LL");
            colorMap.put(Color.WHITE, "WW");
        }
        colorMap.put(Color.EMPTY, "  ");
    }


    public ArrayList<String> printMainShelf(ArrayList<String> output) {
        output.add("          ┌────┬────┬────┬────┬────┐                    .");
        for (Color[] row : mainShelf) {
            StringBuilder string = new StringBuilder("          │");
            for (Color color : row) {
                string
                        .append(colorMap.getOrDefault(color, NULL))
                        .append(colorMap.getOrDefault(color, NULL))
                        .append("│");
            }
            string.append("                    .");
            output.add(string.toString());
            output.add(string.toString());
            output.add("          ├────┼────┼────┼────┼────┤                    .");
        }
        output.remove(output.size() - 1);

        output.add("        ┌─┴────┴────┴────┴────┴────┴─┐                  .");
        output.add("        └────────────────────────────┘                  .");
        return output;
    }

    public void setMainShelf(Color[][] mainShelf) {
        this.mainShelf = mainShelf;
    }

    public static HashMap<Color, String> getColorMap(boolean isColored) {
        enableCLIColors(isColored);
        return colorMap;
    }
}
