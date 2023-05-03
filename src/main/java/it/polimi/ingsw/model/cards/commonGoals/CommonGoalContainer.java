package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewSubject;

import java.io.Serializable;
import java.util.*;

public class CommonGoalContainer implements Iterable<CommonGoalCard>, VirtualViewSubject, Serializable {
    private final int NUMBER_OF_CARDS = 2;
    private final List<VirtualViewObserver> OBSERVERS;
    private final List<CommonGoalCard> COMMON_GOALS;

    private List<Set<String>> completedCommonGoal; //players who completed a specific common goal

    public CommonGoalContainer() {
        COMMON_GOALS = CommonCardDealer.pickCommonGoalCards(NUMBER_OF_CARDS);
        completedCommonGoal = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            completedCommonGoal.add(new HashSet<>());
        }

        OBSERVERS = new ArrayList<>();
    }

    public List<CommonGoalCard> getCommonGoals() {
        return COMMON_GOALS;
    }

    public CommonGoalCard getCommonGoal(int i) {
        return COMMON_GOALS.get(i);
    }

    public CommonGoalCard getCommonGoal(CommonGoalCard cg) {
        return COMMON_GOALS.get(COMMON_GOALS.indexOf(cg));
    }

    public void signCompleted(CommonGoalCard cg, String nickname) {
        completedCommonGoal.get(COMMON_GOALS.indexOf(cg)).add(nickname);
    }

    /**
     * @param cg       the common goal
     * @param nickname the nickname of the player
     * @return if the player has already achieved the common goal
     */
    public boolean isAchieved(CommonGoalCard cg, String nickname) {
        return completedCommonGoal.get(COMMON_GOALS.indexOf(cg)).contains(nickname);
    }

    public List<Set<String>> getCompletedCommonGoal() {
        return completedCommonGoal;
    }

    @Override
    public Iterator<CommonGoalCard> iterator() {
        return COMMON_GOALS.iterator();
    }

    @Override
    public void registerObserver(VirtualViewObserver observer) {
        OBSERVERS.add(observer);
    }

    @Override
    public void removeObserver(VirtualViewObserver observer) {
        OBSERVERS.remove(observer);
    }

    /**
     * this is also called in the board refresher when done refilling the board
     */
    @Override
    public void notifyObservers() {
        for (VirtualViewObserver observer : OBSERVERS) {
            observer.update();
        }
    }
}
