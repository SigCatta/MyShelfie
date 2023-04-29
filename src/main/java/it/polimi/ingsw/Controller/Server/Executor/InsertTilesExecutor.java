package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.InsertTileMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.InsertTilesState;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;

public class InsertTilesExecutor implements Executor {
    private Game game;

    public InsertTilesExecutor(Game game){
        this.game = game;
    }

    @Override
    public void execute(MessageToServer data) {

        InsertTileMessage message = (InsertTileMessage) data;

        if(!(game.getGameState() instanceof InsertTilesState))return;

        TilesGetter tilesGetter = new TilesGetter(game);

        //to insert tiles the player must be the activePlayer of his game
        Player activePlayer = game.getActivePlayer();
        if (!message.getNickname().equals(activePlayer.getNickname())) return;

        try{
            int column = message.getCol();
            int tileIndex = message.getRow();
            tilesGetter.sendTilesToShelf(tileIndex, column);
        }catch (NumberFormatException ignore){} //should not reach

    }
}