package it.polimi.ingsw.Controller.Server.Executor;


import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Client.Messages.PickUpTilesMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.PickUpTilesState;

import java.awt.*;
import java.util.ArrayList;

public class PickupTilesExecutor implements Executor {

    public static void execute(MessageToServer message) {

        Game game = message.getGame();

        PickUpTilesMessage pickUpTilesMessage = (PickUpTilesMessage) message;

        if(!(game.getGameState() instanceof PickUpTilesState))return;
        if (!pickUpTilesMessage.getNickname().equals(game.getActivePlayer().getNickname())) return;


        ArrayList<Point> tilesPosition = pickUpTilesMessage.getTilesPosition();
        if(tilesPosition == null) return;

        game.getTilesGetter().pickUpTiles(tilesPosition);
    }
}