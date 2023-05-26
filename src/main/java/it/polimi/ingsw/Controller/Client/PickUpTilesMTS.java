package it.polimi.ingsw.Controller.Client;


import it.polimi.ingsw.Controller.Server.Executor.PickupTilesExecutor;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Asks to pick up a list of tiles from the board
 */
public class PickUpTilesMTS extends MessageToServer implements Serializable {

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
