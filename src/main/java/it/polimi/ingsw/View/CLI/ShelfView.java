package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Controller.Client.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;
import java.util.HashMap;

public class ShelfView implements ViewElement {
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        Printer.enableCLIColors(true); //TODO delete after testing
        HashMap<Color, String> colorMap = Printer.getColorMap();
        String nickname = SocketClient.getInstance().getNickname();
        Color[][] shelf = ShelvesRepresentation.getInstance().getShelfMessage(nickname).getShelfForCLI();

        output.add("          ┌────┬────┬────┬────┬────┐                    .");
        for (Color[] row : shelf) {
            StringBuilder string = new StringBuilder("          │");
            for (Color color : row) {
                string
                        .append(colorMap.getOrDefault(color, colorMap.get(Color.EMPTY)))
                        .append(colorMap.getOrDefault(color, colorMap.get(Color.EMPTY)))
                        .append(colorMap.getOrDefault(color, colorMap.get(Color.EMPTY)))
                        .append(colorMap.getOrDefault(color, colorMap.get(Color.EMPTY)))
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

}
