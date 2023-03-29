package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule:
 * Quattro tessere dello stesso tipo ai quattro angoli della Libreria.
 */
public class FourAnglesCGS extends CommonGoalStrategy {
    /**
     * @param color    The Color to check.
     * @param itemTile The ItemTile to check.
     * @return True if the ItemTile is not null and has the given color. False otherwise.
     */
    public boolean checkColor(Color color, ItemTile itemTile) {
        return itemTile != null && itemTile.getColor().equals(color);
    }

    /**
     * Checks if the goal of having all tiles on the corners with the same color is achieved.
     *
     * @param shelf the shelf to check
     * @return true if the goal of having all tiles on the corners with the same color is achieved.
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        int rows = shelfGrid.length;
        int cols = shelfGrid[0].length;
        Set<Color> colorSet = new HashSet<>();
        // check the edges
        if (shelfGrid[0][0] != null) colorSet.add(shelfGrid[0][0].getColor());
        if (shelfGrid[0][cols - 1] != null) colorSet.add(shelfGrid[0][cols - 1].getColor());
        if (shelfGrid[rows - 1][cols - 1] != null) colorSet.add(shelfGrid[rows - 1][cols - 1].getColor());
        if (shelfGrid[rows - 1][0] != null) colorSet.add(shelfGrid[rows - 1][0].getColor());

        return colorSet.size() == 1;
    }

    @Override
    public String getDescription() {
        return "Quattro tessere dello stesso tipo ai quattro angoli della Libreria.";
    }

}
