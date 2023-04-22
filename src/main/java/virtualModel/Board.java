package virtualModel;

import it.polimi.ingsw.model.tiles.Color;

public class Board {

    private Color[][] board;

    public Board(){
        board = new Color[9][9];
    }

    public Color[][] getBoard() {
        return board;
    }
}
