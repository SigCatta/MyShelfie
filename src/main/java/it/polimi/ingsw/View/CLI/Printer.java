package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.model.tiles.Color;
import virtualModel.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Printer {

    private Color[][] board;
    HashMap<Color, String> colorMap;
    // Declaration of colored spaces, ANSI_COLOR_ID + white spaces + ANSI_COLOR_RESET
    public final String GREEN_BACKGROUND = "\033[0;102m" + "  " + "\u001B[0m";
    public final String YELLOW_BACKGROUND = "\033[0;103m" + "  " + "\u001B[0m";
    public final String BLUE_BACKGROUND = "\033[0;104m" + "  " + "\u001B[0m";
    public final String PINK_BACKGROUND = "\033[0;105m" + "  " + "\u001B[0m";
    public final String CYAN_BACKGROUND = "\033[0;106m" + "  " + "\u001B[0m";
    public final String WHITE_BACKGROUND = "\033[0;107m" + "  " + "\u001B[0m";
    public final String EMPTY_BACKGROUND = "  ";
    public final String NULL = "..";

    public Printer(boolean isColored) {
        this.board = new Board().getBoard();
        colorMapInit(isColored);
    }

    private void colorMapInit(boolean isColored){
        colorMap = new HashMap<>();
        if (isColored){
            colorMap.put(Color.GREEN, GREEN_BACKGROUND);
            colorMap.put(Color.YELLOW, YELLOW_BACKGROUND);
            colorMap.put(Color.BLUE, BLUE_BACKGROUND);
            colorMap.put(Color.PINK, PINK_BACKGROUND);
            colorMap.put(Color.LIGHTBLUE, CYAN_BACKGROUND);
            colorMap.put(Color.WHITE, WHITE_BACKGROUND);
            colorMap.put(Color.EMPTY, EMPTY_BACKGROUND);
        } else{
            colorMap.put(Color.GREEN, "GG");
            colorMap.put(Color.YELLOW, "YY");
            colorMap.put(Color.BLUE, "BB");
            colorMap.put(Color.PINK, "PP");
            colorMap.put(Color.LIGHTBLUE, "LL");
            colorMap.put(Color.WHITE, "WW");
            colorMap.put(Color.EMPTY, "  ");
        }
    }

    public ArrayList<String> printBoard() { // ─ │ ┌ ┐ └ ┘ ┤ ├ ┴ ┬ ┼
        ArrayList<String> output = new ArrayList<>();
        output.add("┌────┬────┬────┬────┬────┬────┬────┬────┬────┐          .");
        for (Color[] row : board) {
            StringBuilder string = new StringBuilder("│");
            for (Color color : row) {
                string.append(colorMap.getOrDefault(color, NULL)).append(colorMap.getOrDefault(color, NULL)).append("│");
            }
            string.append("          .");
            output.add(string.toString());
            output.add(string.toString());
            output.add("├────┼────┼────┼────┼────┼────┼────┼────┼────┤          .");
        }
        output.remove(output.size() - 1);
        output.add("└────┴────┴────┴────┴────┴────┴────┴────┴────┘          .");
        output.add("                                                        .");
        output.add("                                                        .");
        output.add("                                                        .");

        return output;
    }

    public void setBoard(Color[][] board) {
        this.board = board;
    }
}
