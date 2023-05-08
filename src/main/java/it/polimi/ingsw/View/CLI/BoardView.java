package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualModel.BoardRepresentation;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardView implements ViewElement {
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) { // ─ │ ┌ ┐ └ ┘ ┤ ├ ┴ ┬ ┼
        Color[][] board = BoardRepresentation.getInstance().getBoardColors();
        Printer.enableCLIColors(true); //TODO delete after testing
        HashMap<Color, String> colorMap = Printer.getColorMap();

        output.add("┌──0─┬──1─┬──2─┬──3─┬──4─┬──5─┬──6─┬──7─┬──8─┐          .");
        int rowNumber = 0;
        for (Color[] row : board) {
            StringBuilder string = new StringBuilder("" + rowNumber);
            for (Color color : row) {
                string
                        .append(colorMap.getOrDefault(color, NULL))
                        .append(colorMap.getOrDefault(color, NULL))
                        .append(colorMap.getOrDefault(color, NULL))
                        .append(colorMap.getOrDefault(color, NULL))
                        .append("│");
            }
            string.append("          .");
            output.add(string.toString());
            output.add(string.toString());
            output.add("├────┼────┼────┼────┼────┼────┼────┼────┼────┤          .");
            rowNumber++;
        }
        output.remove(output.size() - 1);
        output.add("└────┴────┴────┴────┴────┴────┴────┴────┴────┘          .");
        output.add("                                                        .");
        output.add("                                                        .");
        output.add("                                                        .");

        return output;
    }
}
