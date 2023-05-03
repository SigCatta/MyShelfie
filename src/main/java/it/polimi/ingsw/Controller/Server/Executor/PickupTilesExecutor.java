package it.polimi.ingsw.Controller.Server.Executor;


import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Client.Messages.PickUpTilesMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.GameState;
import it.polimi.ingsw.model.board.ChosenTilesTable.PickUpValidator;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;

public class PickupTilesExecutor implements Executor {

    public static void execute(MessageToServer message) {

        Game game = message.getGame();

        PickUpTilesMessage pickUpTilesMessage = (PickUpTilesMessage) message;

        if(!(game.getGameState() == GameState.PICK_UP_TILES))return; //TODO send error
        if (!pickUpTilesMessage.getNickname().equals(game.getActivePlayer().getNickname())) return;//TODO send error
        if(!PickUpValidator.isValid(game, pickUpTilesMessage.getTilesPosition())) return;//TODO send error

        ArrayList<Point> tilesPosition = pickUpTilesMessage.getTilesPosition();
        if(tilesPosition == null) return;

        ArrayList<Point> positions = pickUpTilesMessage.getTilesPosition();

        ArrayList<ItemTile> tiles = game.getBoard().removeItemTiles(positions);
        game.getChosenTilesTable().addTiles(tiles);

        game.setGameState(GameState.INSERT_TILES);
    }
}