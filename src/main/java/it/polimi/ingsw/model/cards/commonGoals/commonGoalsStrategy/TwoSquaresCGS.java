package it.polimi.ingsw.model.cards.commonGoals.commonGoalsStrategy;

import it.polimi.ingsw.Enum.Color;
import it.polimi.ingsw.model.cards.commonGoals.CommonGoalStrategy;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

/**
 * Rule:
 * Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles
 * of one square can be different from those of the other square.
 */
public class TwoSquaresCGS extends CommonGoalStrategy {
    /**
     * Checks if a shelfGrid has at least 2 separate groups of 4 cells of the same color which form a 2x2 square.
     *
     * @param shelf - the shelf to check
     * @return true if the matrix has at least 2 separate groups of 4 cells of the same Color that form a 2x2 square, false otherwise.
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        ItemTile[][] shelfGrid = shelf.getShelfGrid();
        boolean[][] visited = new boolean[shelfGrid.length][shelfGrid[0].length];
        int count = 0;

        for (int i = 0; i < shelfGrid.length - 1; i++) { //does not iterate through the lat row
            for (int j = 0; j < shelfGrid[0].length - 1; j++) { //does not iterate through the last column
                if (visited[i][j]) continue;
                visited[i][j] = true;

                try {
                    Color color = shelfGrid[i][j].getColor();
                    if (shelfGrid[i + 1][j].getColor() != color) continue;
                    if (shelfGrid[i][j + 1].getColor() != color) continue;
                    if (shelfGrid[i + 1][j + 1].getColor() != color) continue;

                    visited[i + 1][j] = true;
                    visited[i][j + 1] = true;
                    visited[i + 1][j + 1] = true;

                    count++;
                } catch (NullPointerException ignored) {
                }
            }
        }

        return count >= 2;

    }
}



