package it.polimi.ingsw.model.cards.personalGoals;

import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;
import it.polimi.ingsw.model.tiles.Color;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.awt.Point;
import java.util.HashMap;

public class PersonalGoal {
    HashMap<Color, Point> achievements;
    private final Player player;

    protected PersonalGoal(Player player){
        //TODO implement map fillout from json

        this.player = player;
    }

    public int calculateScore(){
        int score = 0;
        if (!achievements.isEmpty()){
            for(Color color : Color.values()){
                Point point = achievements.get(color);
                Shelf shelf = player.getShelf();
                ItemTile tileAtPoint = shelf.getTileAtLocation(point);
                if (tileAtPoint.getColor() == color){
                    //TODO score += non mi ricordooo
                    achievements.remove(color);
                }
            }
        }
        return score;
    }
}