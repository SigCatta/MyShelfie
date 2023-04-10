package it.polimi.ingsw.Controller.Executor.Server;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class PickupTilesExecutor implements Executor {

    Game game;
    TilesGetter tilesGetter;

    PickupTilesExecutor(Game game) {
        this.game = game;
        this.tilesGetter = new TilesGetter(game);
    }

    @Override
    public void execute(HashMap<String, String> data) {

        if (!data.get("NICKNAME").equals(game.getActivePlayer().getNickname())) return;

        ArrayList<Point> tileLocations = new ArrayList<>();
        int point = 1;
        while (data.containsKey("X" + point)) {
            int x = Integer.parseInt(data.get("X" + point));
            int y = Integer.parseInt(data.get("Y" + point));

            tileLocations.add(new Point(x, y));
            point++;
        }

        if (tilesGetter.pickUpTiles(tileLocations)) {
            //TODO change state and allow the player to insert tiles in the shelf
        } else {
            //TODO send the message "invalid pickup"
        }
    }
}