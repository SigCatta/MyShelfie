package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class PickUpValidator {


    /**
     * check if the player asked for more tiles than possible
     * check if the player asked for consecutive cells
     * check if the player asked for an empty cell
     * @param chosenPositions, list of the tiles
     * @return if the player can take the tiles
     */
    public static boolean isValid(ItemTile[][] boardGrid, ArrayList<Point> chosenPositions) {

        int currentRow = chosenPositions.get(0).x;
        int currentCol = chosenPositions.get(1).y;

        //max number of tiles that the player can take from
        //the board in a single turn
        final int MAX_TILES = 3;

        if(MAX_TILES > chosenPositions.size()) return false;

        if(ArePointsAdjacent(chosenPositions)) return false;

        for (Point position : chosenPositions) {

            //impossible to pick up an empty cell
            if(boardGrid[position.x][position.y] == null) return false;

            if (!hasFreeAdjacentNeighbor(boardGrid, position)) {
                return false;
            }
        }
        return true;
    }


    private static boolean ArePointsAdjacent(ArrayList<Point> points) {

        // get the row and column of the first point in the list
        int row = points.get(0).x;
        int col = points.get(0).y;

        String movingDirection = "";

        for (int i = 1; i < points.size(); i++) {
            Point currPoint = points.get(i);
            int currRow = currPoint.x;
            int currCol = currPoint.y;

            if(currRow != row){
                movingDirection = "col";
                break;
            }else if (currCol != col){
                movingDirection = "row";
                break;
            }
        }

        if(movingDirection.equals("col")){
            points.sort(Comparator.comparingInt(p -> p.x));

            int prevValue = points.get(0).y;
            for(int i = 1; i < points.size(); i++){
                if(points.get(i).x != prevValue + i){
                    return false;
                }
            }

        }else{
            points.sort(Comparator.comparingInt(p -> p.y));

            int prevValue = points.get(0).x;
            for(int i = 1; i < points.size(); i++){
                if(points.get(i).y != prevValue + i){
                    return false;
                }
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


        if(boardGrid[Math.min(0, row-1)][col] == null){
            return true;
        } else if (boardGrid[Math.max(BOARD_DIMENSION, row+1)][col] == null) {
            return true;
        } else if (boardGrid[row][Math.min(0, col-1)] == null){
            return true;
        } else return boardGrid[row][Math.max(0, col + 1)] == null;

    }
}
