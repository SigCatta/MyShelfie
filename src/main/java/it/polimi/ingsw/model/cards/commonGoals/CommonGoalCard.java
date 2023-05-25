package it.polimi.ingsw.model.cards.commonGoals;

import it.polimi.ingsw.VirtualView.ModelObservers.ModelObserver;
import it.polimi.ingsw.VirtualView.ModelObservers.ModelSubject;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Shelf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class CommonGoalCard implements ModelSubject {

    private final ArrayList<ModelObserver> observers;
    /**
     * Stack of points that can be obtained by achieving the goal.
     */
    protected Stack<Integer> pointsStack;

    private final CommonGoalStrategy commonGoalStrategy;
    private final HashSet<Player> playersWhoCompleted;

    public CommonGoalCard(CommonGoalStrategy commonGoalStrategy, int numOfPlayers) {
        playersWhoCompleted = new HashSet<>();
        observers = new ArrayList<>();
        initStackPoints(numOfPlayers);
        this.commonGoalStrategy = commonGoalStrategy;
    }

    private void initStackPoints(int numOfPlayers) {
        pointsStack = new Stack<>();
        switch (numOfPlayers){
            case 2:
                pointsStack.push(4);
                pointsStack.push(8);
            case 3:
                pointsStack.push(4);
                pointsStack.push(6);
                pointsStack.push(8);
            case 4:
                pointsStack.push(2);
                pointsStack.push(4);
                pointsStack.push(6);
                pointsStack.push(8);
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
        if (pointsStack.isEmpty()) return 0;
        return pointsStack.peek();
    }

    public String getCardName() {
        return commonGoalStrategy.getCardName();
    }

    @Override
    public void registerObserver(ModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
       observers.forEach(ModelObserver::update);
    }
}
