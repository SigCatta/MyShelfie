package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;
import java.util.HashMap;

public class ShelfView implements ViewElement {

    /**
     * Prints the player's shelf
     *
     * @param output the ArrayList where to add the shelf
     * @return the given ArrayList with the player's shelf
     */
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        return printShelf(output, SocketClient.getInstance().getNickname());
    }

    /**
     * Prints a give player's shelf
     *
     * @param nickname the plauer's nickname
     * @return an ArrayList containing the given player's shelf
     */
    public ArrayList<String> getOtherShelvsPrint(String nickname) {
        return printShelf(new ArrayList<>(), nickname);
    }

    /**
     * Prints a player's shelf
     *
     * @param output   the ArrayList where to add the shelf
     * @param nickname the player's nickname
     * @return the given ArrayList with the given player's shelf
     */
    private ArrayList<String> printShelf(ArrayList<String> output, String nickname) {
        Printer.enableCLIColors(true); //TODO delete after testing
        HashMap<Color, String> colorMap = Printer.getColorMap();
        Color[][] shelf = ShelvesRepresentation.getInstance().getShelfMessage(nickname).getShelfForCLI();

        output.add("          ┌──0─┬──1─┬──2─┬──3─┬──4─┐                    .");
        int rowNumber = 0;
        for (Color[] row : shelf) {
            StringBuilder string = new StringBuilder("        " + rowNumber + " │");
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
            rowNumber++;
        }
        output.remove(output.size() - 1);

        output.add("        ┌─┴────┴────┴────┴────┴────┴─┐                  .");
        output.add("        └────────────────────────────┘                  .");
        return output;
    }
}
