package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.VirtualView.ModelObservers.VirtualViewSubject;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class CommonGoalCard implements VirtualViewSubject {

    private final ArrayList<VirtualViewObserver> observers;
    /**
     * Stack of points that can be obtained by achieving the goal.
     */
    protected Stack<Integer> pointsStack;

    private final CommonGoalStrategy commonGoalStrategy;
    private final HashSet<Player> playersWhoCompleted;

    public CommonGoalCard(CommonGoalStrategy commonGoalStrategy) {
        playersWhoCompleted = new HashSet<>();
        observers = new ArrayList<>();
        initStackPoints();
        this.commonGoalStrategy = commonGoalStrategy;
    }

    private void initStackPoints() {
        pointsStack = new Stack<>();
        for (int i = 1; i <= 4; i++) {
            pointsStack.push(i * 2);
        }
    }

    /**
     * @param player the player whose score is being calculated
     * @return the point achieved by the player
     */
    public int calculateScore(Player player) {
        if (isGoalAchieved(player.getShelf())) {
            playersWhoCompleted.add(player);
            int points = popPoints();
            notifyObservers();
            return points;
        }
        return 0;
    }

    public boolean hasCompleted(Player player) {
        return playersWhoCompleted.contains(player);
    }

    private boolean isGoalAchieved(Shelf shelf) {
        return commonGoalStrategy.isGoalAchieved(shelf);
    }

    /**
     * Gets the number of points awarded for achieving the goal.
     *
     * @return the number of points awarded
     */
    public int popPoints() {
        return pointsStack.pop();
    }

    public int peekPoints() {
        return pointsStack.peek();
    }

    public String getCardName() {
        return commonGoalStrategy.getCardName();
    }

    @Override
    public void registerObserver(VirtualViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(VirtualViewObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (VirtualViewObserver o : observers) {
            o.update();
        }
    }
}
