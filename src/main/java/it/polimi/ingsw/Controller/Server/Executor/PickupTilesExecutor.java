package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesManager;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class PickupTilesExecutor implements Executor {

    private GamesManager gamesManager;

    PickupTilesExecutor() {
        this.gamesManager = GamesManager.getInstance();
    }

    @Override
    public void execute(HashMap<String, String> data) {
        Game game =  gamesManager.getGame(Integer.parseInt(data.get("GAME_ID")));
        TilesGetter tilesGetter = new TilesGetter(game);


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