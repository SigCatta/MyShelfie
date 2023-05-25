package it.polimi.ingsw.Controller.Client;


import it.polimi.ingsw.Controller.Server.Executor.PickupTilesExecutor;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class PickUpTilesMTS extends MessageToServer implements Serializable {

    private transient int gameID;
    private transient String nickname;

    private final ArrayList<Point> tilesPosition;

    public PickUpTilesMTS(ArrayList<Point> tilesPosition) {
        this.tilesPosition = tilesPosition;
    }

    @Override
    public void update() {
        PickupTilesExecutor.execute(this);
    }

    public ArrayList<Point> getTilesPosition() {
        return tilesPosition;
    }

}
