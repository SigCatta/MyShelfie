package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;

import java.io.Serializable;

public class InsertTileMessage extends MessageToServer implements Serializable {

    private int row, col;

    /**
     * the tile position in the ChosenTilesTable
     */
    private int tilePosition;

    public InsertTileMessage(int tilePosition, int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public void update() {
        ServerController.getInstance().insertTiles(this);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getTilePosition() {
        return tilePosition;
    }

    public void setTilePosition(int tilePosition) {
        this.tilePosition = tilePosition;
    }

}
