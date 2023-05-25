package it.polimi.ingsw.View.GUI.SceneController.Utility;

import it.polimi.ingsw.View.GUI.SceneController.ObjectiveCardController;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.HashMap;

public class CardImagesManager {

    private static final URL COMMON_GOAL_PACKAGE = ObjectiveCardController.class.getClassLoader().getResource("it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/common_goal_cards/angoli_smussati/");
    private static final URL PERSONAL_GOAL_PACKAGE = ObjectiveCardController.class.getClassLoader().getResource("it/polimi/ingsw/View/GUI/17_MyShelfie_BGA/personal_goal_cards/angoli_smussati/");

    private static final HashMap<String, Image> commonGoals = new HashMap<>();

    private static final HashMap<String, Image> personalGoals = new HashMap<>();

    public static Image getCommonGoalImage(String cardName) {
        if (!commonGoals.containsKey(cardName))
            commonGoals.put(cardName, new Image(COMMON_GOAL_PACKAGE + cardName + ".jpg"));
        return commonGoals.get(cardName);
    }

    public static Image getPersonalGoalImage(String cardName) {
        if (!personalGoals.containsKey(cardName))
            personalGoals.put(cardName, new Image(PERSONAL_GOAL_PACKAGE + cardName + ".jpg"));
        return personalGoals.get(cardName);
    }

}
