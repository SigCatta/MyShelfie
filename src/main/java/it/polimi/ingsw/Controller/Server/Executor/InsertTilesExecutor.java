package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.InsertTileMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.GameState;
import it.polimi.ingsw.model.board.ChosenTilesTable.ChosenTilesTable;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;

public class InsertTilesExecutor implements Executor {

    public static void execute(MessageToServer message) {

        Game game = message.getGame();

        InsertTileMessage insertTileMessage = (InsertTileMessage) message;
        ChosenTilesTable chosenTilesTable = game.getChosenTilesTable();

        int tilePosition = insertTileMessage.getTilePosition();
        int column = insertTileMessage.getCol();

        if(!(game.getGameState() == GameState.INSERT_TILES))return;

        //to insert tiles the player must be the activePlayer of his game
        Player activePlayer = game.getActivePlayer();
        if (!insertTileMessage.getNickname().equals(activePlayer.getNickname())) return;

        if(tilePosition >= game.getChosenTilesTable().size() || tilePosition < 0) {
            return;
        }

        if(activePlayer.getShelf().getNumOfBoxLeftInCol(column) >= chosenTilesTable.size()) return;

        if (chosenTilesTable.getChosenColumn() == null) {
            chosenTilesTable.setChosenColumn(insertTileMessage.getCol());
        }
        else if(chosenTilesTable.getChosenColumn() != insertTileMessage.getCol()) return;

        ItemTile tile = chosenTilesTable.popTile(tilePosition);
        activePlayer.getShelf().insertTile(tile, column);

        //there are no more tiles to be inserted
        if(chosenTilesTable.size() == 0){
            game.getTurnHandler().changeTurn();
            game.setGameState(GameState.PICK_UP_TILES);
        }
    }
}