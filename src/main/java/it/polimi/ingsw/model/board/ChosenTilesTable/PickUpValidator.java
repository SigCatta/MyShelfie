package it.polimi.ingsw.model.board.ChosenTilesTable;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class PickUpValidator {

    /**
     * check if the player asked for more tiles than possible then
     * check if the player asked for consecutive cells then
     * check if the player asked for an empty cell then
     * check if the player has enough room for to fit the tiles in his shelf
     * @return if the player can take the tiles
     */
    public static boolean isValid(Game game, ArrayList<Point> chosenPositions) {

        ItemTile[][] boardGrid = game.getBoard().getBoardGrid();
        //max number of tiles that the player can take from the board in a single turn
        final int MAX_TILES = 3;

        if(chosenPositions.size() > MAX_TILES || chosenPositions.size() == 0) return false;

        if(!arePointsAdjacent(chosenPositions)) return false;

        if(tooManyTilesChosen(game, MAX_TILES)) return false;

        for (Point singlePosition : chosenPositions) {
            //impossible to pick up an empty cell
            if(boardGrid[singlePosition.x][singlePosition.y] == null) return false;
            if (!hasFreeAdjacentNeighbor(boardGrid, singlePosition)) return false;
        }

        return true;
    }

    /**
     * Checks if the points in the given list are adjacent.
     * The method sorts the points based on their row or column values, depending on the moving direction, and
     * verifies whether they are consecutive in the sorted order.
     *
     * @param chosenPositions the list of points to be checked for adjacency.
     * @return true, if all points in the list are adjacent, otherwise false.
     */
    private static boolean arePointsAdjacent(ArrayList<Point> chosenPositions) {
        // get the row and column of the first point in the list
        int row = chosenPositions.get(0).x;
        int col = chosenPositions.get(0).y;

        String movingDirection = "";

        for (Point currPoint : chosenPositions) {

            int currRow = currPoint.x;
            int currCol = currPoint.y;

            if(currRow != row){
                //rows are changing
                movingDirection = "row";
                break;
            }else if (currCol != col){
                //columns are changing
                movingDirection = "col";
                break;
            }
        }

        if(movingDirection.equals("row")){
            chosenPositions.sort(Comparator.comparingInt(p -> p.x));

            int prevValue = chosenPositions.get(0).x;
            for(int i = 1; i < chosenPositions.size(); i++){
                if(chosenPositions.get(i).x != prevValue + i){
                    return false;
                }
                if(chosenPositions.get(i).y != col) return false;
            }

        }else{
            chosenPositions.sort(Comparator.comparingInt(p -> p.y));

            int prevValue = chosenPositions.get(0).y;
            for(int i = 1; i < chosenPositions.size(); i++){
                if(chosenPositions.get(i).y != prevValue + i){
                    return false;
                }
                if(chosenPositions.get(i).x != row) return false;
            }
        }
        return true;
    }



    /**
     * checks if the tile taken has at least an adjacent empty cell
     *
     * @param singlePositions, place of the tile the player is trying to take
     * @return if the tile has an adjacent neighbour
     */
    private static boolean hasFreeAdjacentNeighbor(ItemTile[][] boardGrid, Point singlePositions) {

        final int BOARD_DIMENSION = boardGrid.length;

        int row = singlePositions.x;
        int col = singlePositions.y;

        if(singlePositions.x == 0 || singlePositions.x == BOARD_DIMENSION-1
                || singlePositions.y == 0 || singlePositions.y == BOARD_DIMENSION-1) return true;


        if(boardGrid[row-1][col] == null){
            return true;
        } else if (boardGrid[row+1][col] == null) {
            return true;
        } else if (boardGrid[row][col-1] == null){
            return true;
        } else return boardGrid[row][col + 1] == null;

    }

    /**
     * @return true if there isn't any columns with enough free cells to contain all the new tiles
     */
    private static boolean tooManyTilesChosen(Game game, int size) {
        Player activePlayer = game.getActivePlayer();

        Shelf shelf = activePlayer.getShelf();
        for (int i = 0; i < shelf.getCOLUMNS(); i++) {
            if(shelf.getNumOfBoxLeftInCol(i) >= size) return false;    //there is still enough free cell in at least a column
        }
        return true;    //not enough free cell in any columns
    }

}
