package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.*;
import java.util.List;
import java.util.Stack;
/**
 * Rule: Quattro tessere dello stesso tipo ai quattro angoli della Libreria.
 */
public class FourAnglesCG extends CommonGoal{
    /**
     * @param color The Color to check.
     * @param itemTile The ItemTile to check.
     * @return True if the ItemTile is not null and has the given color. False otherwise.
     */
    public boolean checkColor(Color color, ItemTile itemTile){
        if(itemTile==null)   return false;
        return  itemTile.getColor().equals(color);
    }

    /**
     * Checks if the goal of having all tiles on the corners with the same color is achieved.
     *
     * @param shelf the shelf to check
     * @return true if the goal is achieved or false if not
     */
    @Override
    public boolean isGoalAchieved(Shelf shelf) {
        int ROWS = shelf.getROWS();
        int COLUMNS = shelf.getCOLUMNS();
        ItemTile itemTile;

        itemTile = shelf.getTileAtLocation(new Point(0,0));
        if(itemTile==null)   return false;
        Color color = itemTile.getColor();

        itemTile = shelf.getTileAtLocation(new Point(0,COLUMNS-1));
        if(!checkColor(color, itemTile))   return false;


        itemTile = shelf.getTileAtLocation(new Point(ROWS-1,COLUMNS-1));
        if(!checkColor(color, itemTile))   return false;

        itemTile = shelf.getTileAtLocation(new Point(ROWS-1,0));
        if(!checkColor(color, itemTile))   return false;

        return true;
    }
}