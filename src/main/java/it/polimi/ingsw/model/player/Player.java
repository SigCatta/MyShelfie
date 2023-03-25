package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.tiles.ItemTile;
import it.polimi.ingsw.model.cards.personalGoals.PersonalGoal;

import java.util.List;

public class Player {
    private Board board;
    private ShelfManager shelfManager;
    private PersonalGoal personalGoal;
    private String nickname;
    private int score;
    private List<ItemTile> heldTiless;

    public int getScore() {
        return score;
    }

    public Board getBoard() {
        return board;
    }

    public Shelf getShelf() {
        return shelfManager.getShelf();
    }

    public List<ItemTile> getHeldTiless() {
        return heldTiless;
    }

    public void setHeldTiless(List<ItemTile> heldTiless) {
        this.heldTiless = heldTiless;
    }

    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    public void setPersonalGoal(PersonalGoal personalGoal) {
        if (this.personalGoal == null) this.personalGoal = personalGoal;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return nickname;
    }

    public void sortHeldTiles() {
        //TODO once server&client logic is set up
    }

    public void updateScore(int points) {
        score += points;
    }
}
