package it.polimi.ingsw.model.board.BoardRefresher;

public class BoardLookUpTableThree extends BoardLookUpTable {


    private final boolean[][] pointsToBeFilled = {
            {false, false, false, true , false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, true , false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {true , false, false, false, false, false, false, false, false},
            {false, false, true , false, false, false, true , false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true , false, false, false},
    };

    public boolean[][] getPointsToBeFilled() {
        return pointsToBeFilled;
    }
}
