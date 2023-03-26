package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashSet;
import java.util.Set;

/**
 * Rule: Quattro tessere dello stesso tipo ai quattro angoli della Libreria.
 */
public class FourAnglesCG extends CommonGoal {
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
     * @return true if the goal is achieved or false if not
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return hasEdgesWithSameCol(shelf.generateColorMat());
    }

    /**
     * Checks if the goal of having all tiles on the corners with the same color is achieved.
     *
     * @param colorMat the Color matrix to check
     * @return true if the matrix meets the specifics, false otherwise
     */
    public boolean hasEdgesWithSameCol(Color[][] colorMat) {
        int rows = colorMat.length;
        int cols = colorMat[0].length;
        Set<Color> colorSet = new HashSet<>();
        // check the edges
        if (colorMat[0][0] != null) colorSet.add(colorMat[0][0]);
        if (colorMat[0][cols - 1] != null) colorSet.add(colorMat[0][cols - 1]);
        if (colorMat[rows - 1][cols - 1] != null) colorSet.add(colorMat[rows - 1][cols - 1]);
        if (colorMat[rows - 1][0] != null) colorSet.add(colorMat[rows - 1][0]);

        return colorSet.size() == 1;
    }
}
