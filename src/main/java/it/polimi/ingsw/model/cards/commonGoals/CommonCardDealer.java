package it.polimi.ingsw.model.cards.commonGoals;

import exceptions.TooManyCardsRequestedException;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommonCardDealer {
    /**
     * @return a list of all the instances of the classes that extend CommonGoalStrategy.
     */
    public static List<CommonGoalStrategy> getCardStrategies() {
        Reflections reflections = new Reflections("it.polimi.ingsw.model.cards.commonGoals");
        Set<Class<? extends CommonGoalStrategy>> subTypes =  reflections.getSubTypesOf(CommonGoalStrategy.class);

        List<CommonGoalStrategy> instances = new ArrayList<>();
        for (Class<? extends CommonGoalStrategy> subclass : subTypes) {
            try {
                instances.add(subclass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        return instances;
    }

    /**
     * @param number: the number of CommonGoalStrategy  needed
     * @return a list of {@param number} of the instances of the classes that extend CommonGoalStrategy.
     */
    public static List<CommonGoalStrategy> pickCardStrategies(int number) throws TooManyCardsRequestedException {
        List<CommonGoalStrategy> strategyDeck = getCardStrategies();
        List<CommonGoalStrategy> gameCGStrategies = new ArrayList<>();

        if(number > strategyDeck.size())    throw new TooManyCardsRequestedException();
        //arrayList that contains the indexes of the strategies already picked
        List<Integer> indexes = new ArrayList<>();
        int randomNumber = (int) (Math.random() * strategyDeck.size());

        for (int i = 0; i < number; i++) {
            while (indexes.contains(randomNumber)) {
                randomNumber = (int) (Math.random() * strategyDeck.size());
            }
            gameCGStrategies.add(strategyDeck.get(randomNumber));
            indexes.add(randomNumber);
        }

        return gameCGStrategies;
    }

    public static List<CommonGoalCard> pickCommonGoalCards(int number) throws TooManyCardsRequestedException {
        List<CommonGoalCard> commonGoalCards = new ArrayList<>();
        List<CommonGoalStrategy> gameCGStrategies = pickCardStrategies(number);

        for (int i = 0; i < number; i++) {
            CommonGoalCard cg = new CommonGoalCard(gameCGStrategies.get(i));
            commonGoalCards.add(cg);
        }
        return commonGoalCards;
    }
}

