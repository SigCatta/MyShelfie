package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.cards.personalGoals.PersonalGoal;
import it.polimi.ingsw.model.tiles.ItemTile;

import java.util.List;

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

    /**
     * The score of the player.
     */
    private int score;

    /**
     * The list of item tiles held by the player.
     */
    private List<ItemTile> heldTiles;

    public Player() {
        shelf = new Shelf();
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
     * @return the held tiles of the player
     */
    public List<ItemTile> getHeldTiles() {
        return heldTiles;
    }

    /**
     * @param heldTiles the new held tiles of the player
     */
    public void setHeldTiles(List<ItemTile> heldTiles) {
        this.heldTiles = heldTiles;
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
}
