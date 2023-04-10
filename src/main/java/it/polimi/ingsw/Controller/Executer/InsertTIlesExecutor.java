package it.polimi.ingsw.Controller.Executer;

import exceptions.FullColumnException;
import exceptions.NullItemTileException;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.HashMap;
import java.util.List;

public class InsertTIlesExecutor implements Executor {
    Game game;
    TilesGetter tilesGetter;

    InsertTIlesExecutor(Game game) {
        this.game = game;
        this.tilesGetter = new TilesGetter(game);
    }

    @Override
    public void execute(HashMap<String, String> data) {
        Player activePlayer = game.getActivePlayer();
        if (!data.get("NICKNAME").equals(activePlayer.getNickname())) return;

        List<ItemTile> tiles = tilesGetter.getTilesToBeInserted();
        int column = Integer.parseInt(data.get("COLUMN"));
        int tileIndex = Integer.parseInt(data.get("TILE_INDEX"));

        try {
            tilesGetter.sendTilesToShelf(tiles.get(tileIndex), column);
        } catch (FullColumnException e) {
            //TODO send the message "invalid column: not enough room"
            return;
        } catch (NullItemTileException | IndexOutOfBoundsException e){
            //TODO send the message "invalid tile"
        }
        game.getTurnHandler().changeTurn(); //TODO maybe set a timeout before ending the turn?
    }
}