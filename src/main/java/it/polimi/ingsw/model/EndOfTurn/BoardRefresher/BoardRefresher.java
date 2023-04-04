package it.polimi.ingsw.model.EndOfTurn.BoardRefresher;

import it.polimi.ingsw.JSONReader.LookUpTableReader;
import it.polimi.ingsw.model.EndOfTurn.EndOfTurnObserver;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.Bag;


public class BoardRefresher implements EndOfTurnObserver {

    private final Board board;
    private final Bag BAG;
    private final Game GAME;

    /**
     * boolean matrix that contains true in the place where there should be a tile
     */
    private boolean[][] lookUpTable = null;

    public BoardRefresher(Game game) {

        this.board = game.getBoard();
        BAG = game.getBag();
        GAME = game;

        game.getTurnHandler().attachEndOfTurn(this);

    }


    /**
     *  it uses the class BoardLookUpTableJSON to get the lookUpTable from the json database.
     *  Once it gets the matrix, it stores it in the local variable, so it will not be
     *  necessary to query the database multiple times.
     *  Using the lookup table, refillBoard draws tiles from the bag and refills the board
     */
    public void refillBoard() {

        int numberOfPlayers = GAME.getPlayers().size();

        //lookUpTable is initialized once
        if(lookUpTable == null){
            lookUpTable = new LookUpTableReader().getLookUpTable(numberOfPlayers);
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

    @Override
    public void update() {

        if(board == null) throw new NullPointerException();

        if(RefreshTrigger.isBoardRefreshable(board)){
            refillBoard();
        }
    }
}
