package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.model.cards.personalGoals.PersonalGoal;

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
    private List<ItemTile> heldTiless;

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
    public List<ItemTile> getHeldTiless() {
        return heldTiless;
    }

    /**
     * @param heldTiless the new held tiles of the player
     */
    public void setHeldTiless(List<ItemTile> heldTiless) {
        this.heldTiless = heldTiless;
    }

    /**
     * @return the personal goal of the player
     */
    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    /**
     * @return the nickname of the player
     */
    public String getNickname() {
        return nickname;
    }


    @Override
    public String toString() {
        return nickname;
    }

    /**
     * @param points the points to add  to the score
     */
    public void updateScore(int points) {
        score += points;
    }
}
