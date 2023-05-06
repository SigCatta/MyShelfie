package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Server.Executor.InsertTilesExecutor;

import java.io.Serializable;

public class InsertTileMTS extends MessageToServer implements Serializable {

    /**
     * the tile position in the ChosenTilesTable
     */
    private final int TILE_POSITION;
    private final int COL;

    public InsertTileMTS(int tilePosition, int col){
        this.TILE_POSITION = tilePosition;
        this.COL = col;
    }

    @Override
    public void update() {
        InsertTilesExecutor.execute(this);
    }

    public int getCol() {
        return COL;
    }

    public int getTilePosition() {
        return TILE_POSITION;
    }

}
