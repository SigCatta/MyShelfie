package it.polimi.ingsw.model.board.ChosenTilesTable;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;

public class TilesToShelfValidator {
    /**
     * Method called by an observer when the activePlayer chooses the specific order they want to insert the tiles picked up
     *
     * @param tileIndex the index of the tile in the tilesToBeInserted array the activePLayer has chosen to insert into their Shelf
     * @param column the column selected
     * @return true if it was possible to insert the tile,
     *         false if the column selected isn't the corrected one or if the itemTile was already inserted
     */
    public static boolean isValid(Game game, int tileIndex, int column) {

        Player activePlayer = game.getActivePlayer();
        ChosenTilesTable chosenTilesTable = game.getChosenTilesTable();
        Shelf shelf = activePlayer.getShelf();

        if(tileIndex >= game.getChosenTilesTable().size() || tileIndex < 0) {
            return false;
        }

        if(shelf.getNumOfBoxLeftInCol(column) >= chosenTilesTable.size()) return false;

        return true;
    }
}
