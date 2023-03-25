package it.polimi.ingsw.model.board.BoardRefresher;

public class BoardLookUpTableFour extends BoardLookUpTable {

    private final boolean[][] pointsToBeFilled = {
            {false, false, false, false, true , false, false, false, false},
            {false, false, false, false, false, true , false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, true , false, false, false, false, false, false, false},
            {true , false, false, false, false, false, false, false, true },
            {false, false, false, false, false, false, false, true , false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, true , false, false, false, false, false},
            {false, false, false, false, true , false, false, false, false},
    };

    @Override
    boolean[][] getPointsToBeFilled() {
        return pointsToBeFilled;
    }
}
