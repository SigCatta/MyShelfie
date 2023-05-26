package it.polimi.ingsw.View.CLI.Elements.Views;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.JSONReader.LookUpTableReader;
import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Provides the necessary tools to print a drawing of the game's board
 */
public class BoardView extends ViewElement {

    private static BoardView instance;
    private final boolean[][] lookUpTable;

    private BoardView() {
        LookUpTableReader reader = new LookUpTableReader();
        int numOfPlayers = GameRepresentation.getInstance().getGameMessage().getNumOfPlayers();
        lookUpTable = reader.getLookUpTable(numOfPlayers);
    }

    public static BoardView getInstance() {
        if (instance == null) instance = new BoardView();
        return instance;
    }

    /**
     * Prints a drawing of the game's board
     *
     * @param output an ArrayList where to add the drawing
     * @return the give ArrayList extended with the drawing of the game board
     */
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) { // ─ │ ┌ ┐ └ ┘ ┤ ├ ┴ ┬ ┼
        Color[][] board = BoardRepresentation.getInstance().getBoardColors();
        HashMap<Color, String> colorMap = Printer.getColorMap();

        output.add("┌──0─┬──1─┬──2─┬──3─┬──4─┬──5─┬──6─┬──7─┬──8─┐          ");
        int rowNumber = 0;
        int i = 0, j = 0;
        for (Color[] row : board) {
            StringBuilder string = new StringBuilder(Integer.toString(rowNumber));
            for (Color color : row) {
                if (!lookUpTable[i][j]) {
                    string.append(NULL.repeat(4));
                } else {
                    string.append(colorMap.getOrDefault(color, " ").repeat(4));
                }
                string.append("│");
                j++;
            }
            j = 0;
            string.append("          ");
            output.add(string.toString());
            output.add(string.toString());
            output.add("├────┼────┼────┼────┼────┼────┼────┼────┼────┤          ");
            rowNumber++;
            i++;
        }
        output.remove(output.size() - 1);
        output.add("└────┴────┴────┴────┴────┴────┴────┴────┴────┘          ");
        output.add("                                                        ");
        output.add("                                                        ");
        output.add("                                                        ");

        return output;
    }
}
