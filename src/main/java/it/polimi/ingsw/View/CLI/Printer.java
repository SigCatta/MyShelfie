package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.model.tiles.Color;
import virtualModel.Board;

import java.util.ArrayList;
import java.util.HashMap;

public class Printer {

    private Color[][] board;
    HashMap<Color, String> colorMap;
    // Declaration of colored spaces, ANSI_COLOR_ID + white spaces + ANSI_COLOR_RESET
    public final String GREEN = "\033[0;102m" + "    " + "\u001B[0m" + "│";
    public final String YELLOW = "\033[0;103m" + "    " + "\u001B[0m" + "│";
    public final String BLUE = "\033[0;104m" + "    " + "\u001B[0m" + "│";
    public final String PINK = "\033[0;105m" + "    " + "\u001B[0m" + "│";
    public final String CYAN = "\033[0;106m" + "    " + "\u001B[0m" + "│";
    public final String WHITE = "\033[0;107m" + "    " + "\u001B[0m" + "│";
    public final String EMPTY = "    " + "│";
    public final String NULL = "null" + "│";

    public Printer() {
        this.board = new Board().getBoard();
        colorMap = new HashMap<>();
        colorMap.put(Color.GREEN, GREEN);
        colorMap.put(Color.YELLOW, YELLOW);
        colorMap.put(Color.BLUE, BLUE);
        colorMap.put(Color.PINK, PINK);
        colorMap.put(Color.LIGHTBLUE, CYAN);
        colorMap.put(Color.WHITE, WHITE);
        colorMap.put(Color.EMPTY, EMPTY);
    }

    public void printBoard() { // ─ │ ┌ ┐ └ ┘ ┤ ├ ┴ ┬ ┼
        ArrayList<String> output = new ArrayList<>();
        output.add("┌────┬────┬────┬────┬────┬────┬────┬────┬────┐");
        for (Color[] row : board) {
            StringBuilder string = new StringBuilder("│");
            for (Color color : row) {
                string.append(colorMap.getOrDefault(color, NULL));
            }
            output.add(string.toString());
            output.add(string.toString());
            output.add("├────┼────┼────┼────┼────┼────┼────┼────┼────┤");
        }
        output.remove(output.size() - 1);
        output.add("└────┴────┴────┴────┴────┴────┴────┴────┴────┘");

        for (String line : output) {
            System.out.println(line);
        }
    }

    public void setBoard(Color[][] board) {
        this.board = board;
    }
}
