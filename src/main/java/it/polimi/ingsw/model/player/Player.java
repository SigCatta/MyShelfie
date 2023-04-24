package it.polimi.ingsw.model.player;

import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewObserver;
import it.polimi.ingsw.View.VirtualView.ModelObservers.VirtualViewSubject;
import it.polimi.ingsw.model.cards.personalGoals.PersonalGoal;

import java.util.ArrayList;

/**
 * Represents a player in the game.
 */
public class Player implements VirtualViewSubject {
    private ArrayList<VirtualViewObserver> observers;

    private Shelf shelf;

    /**
     * The personal goal of the player.
     */
    private PersonalGoal personalGoal;

    /**
     * The nickname of the player.
     */
    private String nickname;
    private boolean isConnected;
    /**
     * The score of the player.
     */
    private int score;

    public Player(String nickname) {
        observers = new ArrayList<>();
        this.nickname = nickname;
        this.shelf = new Shelf();
        this.isConnected = true;
    }

    /**
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the shelf of the player
     */
    public Shelf getShelf() {
        return shelf;
    }

    /**
     * @return the personal goal of the player
     */
    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

     /**
     * Assigns a personal goal to a player if the player has not personal goals assigned yet
     *
     * @param personalGoal the personal goal which is going to be assigned to the player
     */
    public void setPersonalGoal(PersonalGoal personalGoal) {
        if (this.personalGoal == null) this.personalGoal = personalGoal;
        notifyObservers();
    }

     /**
     * @return the nickname of the player
     */
    public String getNickname() {
        return nickname;
    }


    /**
     * @param points the points to add  to the score
     */
    public void updateScore(int points) {
        score += points;
        notifyObservers();
    }

    public void setConnected(boolean connected) {
        this.isConnected = connected;
        notifyObservers();
    }

    public boolean isConnected() {
        return isConnected;
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
        for(VirtualViewObserver observer : observers){
            observer.update();
        }
    }
}
