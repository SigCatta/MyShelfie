package it.polimi.ingsw.View.CLI.Elements;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.VirtualModel.TilesTableRepresentation;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TilesTableView implements ViewElement {
    /**
     * Prints a box containing the tiles picked up from the player
     *
     * @param output where to add the box
     * @return the give ArrayList with the box added
     */
    @Override
    public ArrayList<String> getPrint(ArrayList<String> output) {
        HashMap<Color, String> colorMap = Printer.getColorMap();
        List<ItemTile> tiles = TilesTableRepresentation.getInstance().getTiles();

        output.add("┌────────────────┐");

        StringBuilder string = new StringBuilder("│ ");
        for (ItemTile tile : tiles) {
            String color = colorMap.get(tile.getColor());
            string.append(color.repeat(4))
                    .append(" ");
        }
        string.append("     ".repeat(3 - tiles.size())); // adds padding for each missing tile
        string.append("│");

        output.add(string.toString());
        output.add(string.toString());
        output.add("└────────────────┘");
        return output;
    }
}
