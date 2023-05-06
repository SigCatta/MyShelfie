package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Controller.Client.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.model.tiles.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardView implements ViewElement {
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) { // ─ │ ┌ ┐ └ ┘ ┤ ├ ┴ ┬ ┼
        Color[][] board = BoardRepresentation.getInstance().getBoardColors();
        Printer.enableCLIColors(true); //TODO delete after testing
        HashMap<Color, String> colorMap = Printer.getColorMap();

        output.add("┌────┬────┬────┬────┬────┬────┬────┬────┬────┐          .");
        for (Color[] row : board) {
            StringBuilder string = new StringBuilder("│");
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
        }
        output.remove(output.size() - 1);
        output.add("└────┴────┴────┴────┴────┴────┴────┴────┴────┘          .");
        output.add("                                                        .");
        output.add("                                                        .");
        output.add("                                                        .");

        return output;
    }
}
