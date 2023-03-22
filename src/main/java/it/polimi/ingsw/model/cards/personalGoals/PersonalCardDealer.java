package it.polimi.ingsw.model.cards.personalGoals;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersonalCardDealer {
    private JSONObject jsonObject;

    public static List<PersonalGoal> getCards(int numOfPlayers) {
        List<PersonalGoal> drawnCards = new ArrayList<>();
        //TODO implement random draw from json db
        return drawnCards;
    }

}
