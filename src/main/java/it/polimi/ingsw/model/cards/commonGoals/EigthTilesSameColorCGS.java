package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Rule:
 * Otto tessere dello stesso tipo. Non ci
 * sono restrizioni sulla posizione di queste tessere.
 */
public class EigthTilesSameColorCGS extends CommonGoalStrategy {
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        return hasAtLeastEightCellsOfSameColor(shelf.generateColorMat());
    }

    @Override
    public String getDescription() {
        return "Otto tessere dello stesso tipo. Non ci sono restrizioni sulla posizione di queste tessere.";
    }

    /**
     * @param colorMat the Color matrix to be checked
     * @return true if at least eight cells with the same color are found, false
    otherwise
     */
    public boolean hasAtLeastEightCellsOfSameColor(Color[][] colorMat) {
        Map<Color, Integer> colorsCount = new HashMap<>();
        for (Color[] row : colorMat) {
            for (Color cell : row) {
                if (cell != null) {
                    int count = colorsCount.getOrDefault(cell, 0);
                    colorsCount.put(cell, count + 1);
                    if (count + 1 >= 8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
