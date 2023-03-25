package it.polimi.ingsw.model.cards.commonGoals;

import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommonCardDealer {
    /**
     * @return a list of all the instances of the classes that extend CommonGoal.
     */
    public static List<CommonGoal> getCards() {
        Reflections reflections = new Reflections("it.polimi.ingsw.model.cards.commonGoals");
        Set<Class<? extends CommonGoal>> subTypes =  reflections.getSubTypesOf(CommonGoal.class);

        List<CommonGoal> instances = new ArrayList<>();
        for (Class<? extends CommonGoal> subclass : subTypes) {
            try {
                instances.add(subclass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return instances;
    }

    /**
     * @param number: the number of CommonGoal card needed
     * @return a list of {@param number} of the instances of the classes that extend CommonGoal.
     */
    public static List<CommonGoal> pickCards(int number) {
        List<CommonGoal> deck = getCards();
        List<CommonGoal> gameCGCards = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            int randomNumber = (int) (Math.random() * deck.size());
            gameCGCards.add(deck.get(randomNumber));
        }

        return gameCGCards;
    }
}

