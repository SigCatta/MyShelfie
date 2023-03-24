package it.polimi.ingsw.model.board.BoardRefresher;

public class BoardLookUpTableTwo extends BoardLookUpTable {

    private final boolean[][] pointsToBeFilled = {
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, true , true , false, false, false, false},
            {false, false, false, true , true , true , true , false, false},
            {false, false, true , true , true , true , true , true , false},
            {false, true , true , true , true , true , true , true , false},
            {false, true , true , true , true , true , true , false, false},
            {false, false, false, true , true , true , false, false, false},
            {false, false, false, false, true , true , false, false, false},
            {false, false, false, false, false, false, false, false, false}
    };


    @Override
    boolean[][] getPointsToBeFilled() {
        return pointsToBeFilled;
    }
}
