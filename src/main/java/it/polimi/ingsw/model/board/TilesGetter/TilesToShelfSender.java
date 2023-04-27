package it.polimi.ingsw.model.board.TilesGetter;

import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.PickUpTilesState;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;

public class TilesToShelfSender {

    private Game game;
    private ChosenTilesTable chosenTilesTable;
    private Integer chosenColumn = null;
    public TilesToShelfSender(Game game, ChosenTilesTable chosenTilesTable){
        this.game = game;
        this.chosenTilesTable = chosenTilesTable;
    }

    /**
     * Method called by an observer when the activePlayer chooses the specific order they want to insert the tiles picked up
     *
     * @param tileIndex the index of the tile in the tilesToBeInserted array the activePLayer has chosen to insert into their Shelf
     * @param column the column selected
     * @return true if it was possible to insert the tile,
     *         false if the column selected isn't the corrected one or if the itemTile was already inserted
     */
    public boolean sendTilesToShelf(int tileIndex, int column) {

        Player activePlayer = game.getActivePlayer();

        if(tileIndex >= chosenTilesTable.size() || tileIndex < 0) {
            game.getVirtualView().send(new ErrorMessage("ERROR IN THE CREATION OF THE COMMAND"));
            return false;
        }

        //check if the number of tiles to insert fit in the chosen column
        if(this.chosenColumn == null && !enoughFreeCellsInCol(column)) {
            game.getVirtualView().send(new ErrorMessage("The chosen column can't contain your tiles"));
            return false;
        }

        //here the chosen column is definitive
        if(this.chosenColumn == null) this.chosenColumn = column;

        //check if the player chosen column is the right one
        if(column != this.chosenColumn) {
            game.getVirtualView().send(new ErrorMessage("You must put your tiles in the same column"));
            return false;
        }

        ItemTile tileToInsert = chosenTilesTable.popTile(tileIndex);
        if(!activePlayer.getShelf().insertTile(tileToInsert, column)) {
            game.getVirtualView().send(new ErrorMessage("THE SERVER HAD PROBLEMS IN INSERTING THE TILE"));
            return false; //should not reach
        }

        //there are no more tiles to be inserted
        if(chosenTilesTable.size() == 0){
            game.getTurnHandler().changeTurn();
            game.setGameState(new PickUpTilesState());
            chosenColumn = null;
        }

        return true;
    }

    private boolean enoughFreeCellsInCol(int column) {
        return game.getActivePlayer().getShelf().getNumOfBoxLeftInCol(column) >= chosenTilesTable.size();
    }
}
