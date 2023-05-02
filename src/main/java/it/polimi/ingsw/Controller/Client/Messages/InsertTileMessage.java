package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;

import java.io.Serializable;

public class InsertTileMessage extends MessageToServer implements Serializable {

    /**
     * the tile position in the ChosenTilesTable
     */
    private final int TILE_POSITION;
    private final int COL;

    public InsertTileMessage(int tilePosition, int col){
        this.TILE_POSITION = tilePosition;
        this.COL = col;
    }

    @Override
    public void update() {
        ServerController.getInstance().insertTiles(this);
    }

    public int getCol() {
        return COL;
    }

    public int getTilePosition() {
        return TILE_POSITION;
    }

}
