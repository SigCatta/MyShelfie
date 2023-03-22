package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class PickUpValidator {


    /**
     * check if the player asked for more tiles than possible then
     * check if the player asked for consecutive cells then
     * check if the player asked for an empty cell
     * @param chosenPositions, list of the tiles
     * @return if the player can take the tiles
     */
    public static boolean isValid(ItemTile[][] boardGrid, ArrayList<Point> chosenPositions) {

        //max number of tiles that the player can take from the board in a single turn
        final int MAX_TILES = 3;

        if(chosenPositions.size() > MAX_TILES || chosenPositions.size() == 0) return false;

        if(!ArePointsAdjacent(chosenPositions)) return false;

        for (Point position : chosenPositions) {

            //impossible to pick up an empty cell
            if(boardGrid[position.x][position.y] == null) return false;

            if (!hasFreeAdjacentNeighbor(boardGrid, position)) return false;

        }

        return true;
    }


    static boolean ArePointsAdjacent(ArrayList<Point> points) {
        // get the row and column of the first point in the list
        int row = points.get(0).x;
        int col = points.get(0).y;

        String movingDirection = "";

        for (Point currPoint : points) {

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
            points.sort(Comparator.comparingInt(p -> p.x));

            int prevValue = points.get(0).x;
            for(int i = 1; i < points.size(); i++){
                if(points.get(i).x != prevValue + i){
                    return false;
                }
                if(points.get(i).y != col) return false;
            }

        }else{
            points.sort(Comparator.comparingInt(p -> p.y));

            int prevValue = points.get(0).y;
            for(int i = 1; i < points.size(); i++){
                if(points.get(i).y != prevValue + i){
                    return false;
                }
                if(points.get(i).x != row) return false;
            }
        }
        return true;
    }



    /**
     * checks if the tile taken has at least an adjacent empty cell
     *
     * @param boardGrid, the board representation containing the tiles
     * @param position, place of the tile the player is trying to take
     * @return if the tile has an adjacent neighbour
     */
    private static boolean hasFreeAdjacentNeighbor(ItemTile[][] boardGrid, Point position) {

        final int BOARD_DIMENSION = boardGrid.length;

        int row = position.x;
        int col = position.y;

        if(position.x == 0 || position.x == BOARD_DIMENSION-1
                || position.y == 0 || position.y == BOARD_DIMENSION-1) return true;


        if(boardGrid[row-1][col] == null){
            return true;
        } else if (boardGrid[row+1][col] == null) {
            return true;
        } else if (boardGrid[row][col-1] == null){
            return true;
        } else return boardGrid[row][col + 1] == null;

    }
}
