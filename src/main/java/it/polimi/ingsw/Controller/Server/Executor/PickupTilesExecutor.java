package it.polimi.ingsw.Controller.Server.Executor;


import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.TilesGetter.TilesGetter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class PickupTilesExecutor implements Executor {

    private Game game;

    public PickupTilesExecutor(Game game){
        this.game = game;
    }


    @Override
    public void execute(HashMap<String, String> data) {

        String command = data.get("COMMAND");
        if(command == null) return;

        if(!game.getGameState().isCommandPossible(command))return;

        if (!data.get("NICKNAME").equals(game.getActivePlayer().getNickname())) return;

        TilesGetter tilesGetter = game.getTilesGetter();

        ArrayList<Point> tileLocations = new ArrayList<>();
        int point = 1;
        while (data.containsKey("X" + point)) {
            int x = Integer.parseInt(data.get("X" + point));
            int y = Integer.parseInt(data.get("Y" + point));

            tileLocations.add(new Point(x, y));
            point++;
        }

        tilesGetter.pickUpTiles(tileLocations);
    }
}