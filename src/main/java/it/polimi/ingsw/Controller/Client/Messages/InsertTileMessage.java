package it.polimi.ingsw.Controller.Client.Messages;

import it.polimi.ingsw.Controller.Server.ServerController.ServerController;
import it.polimi.ingsw.model.Game;

public class InsertTileMessage extends MessageToServer{

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
    public void update(Game game) {
        ServerController.getInstance().insertTiles(this, game);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
