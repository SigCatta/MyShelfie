package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashMap;
import java.util.Map;

/**
 * Rule:
 * Otto tessere dello stesso tipo. Non ci
 * sono restrizioni sulla posizione di queste tessere.
 */
public class eightTilesSameColorCGS extends CommonGoalStrategy {
    /**
     * @param shelf the shelf to be checked
     * @return true if at least eight cells with the same color are found, false otherwise
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        Map<Color, Integer> colorsCount = new HashMap<>();
        for (ItemTile[] row : shelfGrid) {
            for (ItemTile cell : row) {
                if (cell != null) {
                    int count = colorsCount.getOrDefault(cell.getColor(), 0);
                    colorsCount.put(cell.getColor(), count + 1);
                    if (count + 1 >= 8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Otto tessere dello stesso tipo. Non ci sono restrizioni sulla posizione di queste tessere.";
    }



}
