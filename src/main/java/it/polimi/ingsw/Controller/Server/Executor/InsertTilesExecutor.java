package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Commands.CommandMapKey;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;

public class InsertTilesExecutor implements Executor {
    private Game game;

    public InsertTilesExecutor(Game game){
        this.game = game;
    }

    @Override
    public void execute(HashMap<String, String> data) {
        String command = data.get(String.valueOf(CommandMapKey.COMMAND));
        if(command == null) return;

        if(!game.getGameState().isCommandPossible(command))return;

        TilesGetter tilesGetter = new TilesGetter(game);

        //to insert tiles the player must be the activePlayer of his game
        Player activePlayer = game.getActivePlayer();
        if (!data.get(String.valueOf(CommandMapKey.NICKNAME)).equals(activePlayer.getNickname())) return;

        try{
            int column = Integer.parseInt(data.get(String.valueOf(CommandMapKey.COLUMN)));
            int tileIndex = Integer.parseInt(data.get(String.valueOf(CommandMapKey.TILE_INDEX)));
            tilesGetter.sendTilesToShelf(tileIndex, column);
        }catch (NumberFormatException ignore){} //should not reach

    }
}