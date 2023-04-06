package it.polimi.ingsw.model.board.TilesGetter;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class PickUpValidator {


    private final ItemTile[][] BOARD_GRID;
    //max number of tiles that the player can take from the board in a single turn
    final int MAX_TILES;

    public PickUpValidator(Game game){
        BOARD_GRID = game.getBoard().getBoardGrid();
        MAX_TILES = game.getMAX_TILES_FROM_BOARD();
    }


    /**
     * check if the player asked for more tiles than possible then
     * check if the player asked for consecutive cells then
     * check if the player asked for an empty cell
     * @return if the player can take the tiles
     */
    public boolean isValid(ArrayList<Point> chosenPositions) {

        if(chosenPositions.size() > MAX_TILES || chosenPositions.size() == 0) return false;

        if(!arePointsAdjacent(chosenPositions)) return false;

        for (Point singlePosition : chosenPositions) {

            //impossible to pick up an empty cell
            if(BOARD_GRID[singlePosition.x][singlePosition.y] == null) return false;

            if (!hasFreeAdjacentNeighbor(singlePosition)) return false;

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
    boolean arePointsAdjacent(ArrayList<Point> chosenPositions) {
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
    private boolean hasFreeAdjacentNeighbor(Point singlePositions) {

        final int BOARD_DIMENSION = BOARD_GRID.length;

        int row = singlePositions.x;
        int col = singlePositions.y;

        if(singlePositions.x == 0 || singlePositions.x == BOARD_DIMENSION-1
                || singlePositions.y == 0 || singlePositions.y == BOARD_DIMENSION-1) return true;


        if(BOARD_GRID[row-1][col] == null){
            return true;
        } else if (BOARD_GRID[row+1][col] == null) {
            return true;
        } else if (BOARD_GRID[row][col-1] == null){
            return true;
        } else return BOARD_GRID[row][col + 1] == null;

    }
}
