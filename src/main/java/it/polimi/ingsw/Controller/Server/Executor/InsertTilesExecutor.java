package it.polimi.ingsw.Controller.Server.Executor;

import exceptions.FullColumnException;
import exceptions.NullItemTileException;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesManager;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashMap;
import java.util.List;

public class InsertTilesExecutor implements Executor {
    private GamesManager gamesManager;

    public InsertTilesExecutor() {
        gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {
        Game game = gamesManager.getGame(Integer.parseInt(data.get("GAMEID")));
        if(!game.getGameState().isCommandPossible(data.get("COMMAND")))return;

        TilesGetter tilesGetter = new TilesGetter(game);

        //to insert tiles the player must be the activePlayer of his game
        Player activePlayer = game.getActivePlayer();
        if (!data.get("NICKNAME").equals(activePlayer.getNickname())) return;

        int column = Integer.parseInt(data.get("COLUMN"));
        int tileIndex = Integer.parseInt(data.get("TILE_INDEX"));

        tilesGetter.sendTilesToShelf(tileIndex, column);
    }
}