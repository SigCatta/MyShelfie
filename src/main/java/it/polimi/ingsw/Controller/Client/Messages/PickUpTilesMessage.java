package it.polimi.ingsw.Controller.Client.Messages;


import it.polimi.ingsw.Controller.Server.ServerController.ServerController;
import it.polimi.ingsw.model.Game;

import java.awt.*;
import java.util.ArrayList;

public class PickUpTilesMessage extends MessageToServer {

    private transient int gameID;
    private transient String nickname;

    private ArrayList<Point> tilesPosition;

    public PickUpTilesMessage(ArrayList<Point> tilesPosition){
        this.tilesPosition = tilesPosition;
    }

    @Override
    public void update(Game game) {
        ServerController.getInstance().pickUpTiles(this, game);
    }

    public ArrayList<Point> getTilesPosition() {
        return tilesPosition;
    }

    public void addPosition(Point point){
        this.tilesPosition.add(point);
    }

}
