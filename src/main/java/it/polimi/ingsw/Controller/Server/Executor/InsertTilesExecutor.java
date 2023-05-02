package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.Messages.InsertTileMessage;
import it.polimi.ingsw.Controller.Client.Messages.MessageToServer;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameState.InsertTilesState;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;

public class InsertTilesExecutor implements Executor {

    public static void execute(MessageToServer message) {

        Game game = message.getGame();

        InsertTileMessage insertTileMessage = (InsertTileMessage) message;

        if(!(game.getGameState() instanceof InsertTilesState))return;

        TilesGetter tilesGetter = new TilesGetter(game);

        //to insert tiles the player must be the activePlayer of his game
        Player activePlayer = game.getActivePlayer();
        if (!insertTileMessage.getNickname().equals(activePlayer.getNickname())) return;

        try{
            int column = insertTileMessage.getCol();
            int tileIndex = insertTileMessage.getRow();
            tilesGetter.sendTilesToShelf(tileIndex, column);
        }catch (NumberFormatException ignore){} //should not reach

    }
}