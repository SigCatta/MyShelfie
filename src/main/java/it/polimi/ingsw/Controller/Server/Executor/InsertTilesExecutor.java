package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.InsertTileMTS;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.ChosenTilesTable.ChosenTilesTable;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.network.server.SocketClientHandler;

public class InsertTilesExecutor implements Executor {

    public static void execute(InsertTileMTS insertTileMessage) {

        Game game = insertTileMessage.getGame();
        SocketClientHandler handler = insertTileMessage.getSocketClientHandler();
        ChosenTilesTable chosenTilesTable = game.getChosenTilesTable();

        int tilePosition = insertTileMessage.getTilePosition();
        int column = insertTileMessage.getCol();

        if (!(game.getGameState() == GameState.INSERT_TILES)) {
            handler.sendCommand(new EchoMTC(EchoID.PANIC, true)); //the client should stop this (should not reach)
            return;
        }

        //to insert tiles the player must be the activePlayer of his game
        Player activePlayer = game.getActivePlayer();
        if (!insertTileMessage.getNickname().equals(activePlayer.getNickname())) {
            handler.sendCommand(new EchoMTC(EchoID.PANIC, true)); //the client should stop this (should not reach)
            return;
        }

        if (tilePosition >= game.getChosenTilesTable().size() || tilePosition < 0) {
            handler.sendCommand(new EchoMTC(EchoID.WRONG_CHOSEN_TABLE_INDEX, true));
            return;
        }

        if (activePlayer.getShelf().getNumOfBoxLeftInCol(column) < chosenTilesTable.size()) {
            handler.sendCommand(new EchoMTC(EchoID.BADCOLUMN, true));
            return;
        }

        if (chosenTilesTable.getChosenColumn() == null) {
            chosenTilesTable.setChosenColumn(insertTileMessage.getCol());
        } else if (chosenTilesTable.getChosenColumn() != insertTileMessage.getCol()) {
            handler.sendCommand(new EchoMTC(EchoID.COLUMNDIFF, true));
            return;
        }

        ItemTile tile = chosenTilesTable.popTile(tilePosition);
        activePlayer.getShelf().insertTile(tile, column);

        //there are no more tiles to be inserted
        if(chosenTilesTable.size() == 0){
            game.getTurnHandler().changeTurn();
            chosenTilesTable.setChosenColumn(null);
            game.setGameState(GameState.PICK_UP_TILES);
        }
        game.getVirtualView().send(new EchoMTC(EchoID.CHANGE, false));
    }
}