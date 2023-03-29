package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.tiles.ItemTile;

public class RefreshTrigger {

    public static boolean isBoardRefreshable(Board board){
        ItemTile[][] boardGrid = board.getBoardGrid();

        // Check if the board has only isolated tiles
        for(int i=0; i<boardGrid.length; i++){
            for(int j=0; j<boardGrid[i].length; j++){

                if(boardGrid[i][j] == null) continue;

                if(i > 0 && boardGrid[i-1][j] != null){
                    return false;
                }
                if(i < boardGrid.length-1 && boardGrid[i+1][j] != null){
                    return false;
                }
                if(j > 0 && boardGrid[i][j-1] != null){
                    return false;
                }
                if(j < boardGrid[i].length-1 && boardGrid[i][j+1] != null){
                    return false;
                }
            }
        }
        return true;
    }

}