package it.polimi.ingsw.Controller.Server.Executor;


import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.Controller.Client.Messages.PickUpTilesMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.PickUpTilesState;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;

import java.awt.*;
import java.util.ArrayList;

public class PickupTilesExecutor implements Executor {

    private Game game;

    public PickupTilesExecutor(Game game){
        this.game = game;
    }


    @Override
    public void execute(MessageToServer data) {

        PickUpTilesMessage pickUpTilesMessage = (PickUpTilesMessage) data;

        if(!(game.getGameState() instanceof PickUpTilesState))return;
        if (!pickUpTilesMessage.getNickname().equals(game.getActivePlayer().getNickname())) return;


        ArrayList<Point> tilesPosition = pickUpTilesMessage.getTilesPosition();
        if(tilesPosition == null) return;

        game.getTilesGetter().pickUpTiles(tilesPosition);
    }
}