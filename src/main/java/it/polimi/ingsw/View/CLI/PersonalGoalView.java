package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.Controller.Client.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.model.tiles.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonalGoalView {
    public ArrayList<String> printPersonalGoal(ArrayList<String> output){
        HashMap<Color, Point> personalGoal = PlayersRepresentation.getInstance().getPlayerByNickname("nickname").getPersonalGoal().getAchievements();
        return new ArrayList<>(); //TODO fixed in the next commit
    }
}
