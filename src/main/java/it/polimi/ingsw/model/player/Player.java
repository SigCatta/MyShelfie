package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.cards.personalGoals.PersonalGoal;

/**
 * Represents a player in the game.
 */
public class Player {
    /**
     * The board where items can be picked up from.
     */
    private Board board;

    /**
     * The shelf manager that controls the shelves owned by the player.
     */
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

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * @return the board of the player
     */
    public Board getBoard() {
        return board;
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
    }

    public void setConnected(boolean connected) {
        this.isConnected = connected;
    }

    public boolean isConnected() {
        return isConnected;
    }
}
