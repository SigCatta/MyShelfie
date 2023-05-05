package it.polimi.ingsw.Controller.Client.Messages;


import it.polimi.ingsw.Controller.Server.Executor.PickupTilesExecutor;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class PickUpTilesMessage extends MessageToServer implements Serializable {

    private transient int gameID;
    private transient String nickname;

    private ArrayList<Point> tilesPosition;

    public PickUpTilesMessage(ArrayList<Point> tilesPosition){
        this.tilesPosition = tilesPosition;
    }

    @Override
    public void update() {
        PickupTilesExecutor.execute(this);
    }

    public ArrayList<Point> getTilesPosition() {
        return tilesPosition;
    }

    public void addPosition(Point point){
        this.tilesPosition.add(point);
    }

}
