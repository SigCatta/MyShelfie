package it.polimi.ingsw.model.board;

import it.polimi.ingsw.JSONReader.LookUpTableReader;
import it.polimi.ingsw.model.tiles.Bag;


public class BoardRefresher {

    private final Board board;
    private final Bag BAG;
    private final int NUMBER_OF_PLAYERS;

    /**
     * boolean matrix that contains true in the place where there should be a tile
     */
    private boolean[][] lookUpTable = null;

    public BoardRefresher(Board board, Bag bag, int numberOfPlayers) {

        this.board = board;
        BAG = bag;
        NUMBER_OF_PLAYERS = numberOfPlayers;

    }

    /**
     *  it uses the class BoardLookUpTableJSON to get the lookUpTable from the json database.
     *  Once it gets the matrix, it stores it in the local variable, so it will not be
     *  necessary to query the database multiple times.
     *  Using the lookup table, refillBoard draws tiles from the bag and refills the board
     */
    public void refillBoard() {

        if(lookUpTable == null){
            lookUpTable = new LookUpTableReader().getLookUpTable(NUMBER_OF_PLAYERS);
        }

        for(int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){

                if(board.getBoardGrid()[i][j] != null) continue;

                if(lookUpTable[i][j]){
                    board.getBoardGrid()[i][j] = BAG.drawTile();
                }
            }
        }
    }

}
