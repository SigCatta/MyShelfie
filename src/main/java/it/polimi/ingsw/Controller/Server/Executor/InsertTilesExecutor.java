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
        Game game = gamesManager.getGame(Integer.parseInt(data.get("GAME_ID")));
        TilesGetter tilesGetter = new TilesGetter(game);

        Player activePlayer = game.getActivePlayer();
        if (!data.get("NICKNAME").equals(activePlayer.getNickname())) return;

        List<ItemTile> tiles = tilesGetter.getTilesToBeInserted();
        int column = Integer.parseInt(data.get("COLUMN"));
        int tileIndex = Integer.parseInt(data.get("TILE_INDEX"));
        if(tileIndex > tiles.size() || tileIndex < 0) {
            //TODO send the message "invalid position"
        }

        try {
            tilesGetter.sendTilesToShelf(tileIndex, column);
        } catch (FullColumnException e) {
            //TODO send the message "invalid column: not enough room"
            return;
        } catch (NullItemTileException | IndexOutOfBoundsException e){
            //TODO send the message "invalid tile"
        }
    }
}