package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cardCreation.PersonalGoal;
import it.polimi.ingsw.model.gameItems.Tiles.ItemTile;

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

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return nickname;
    }

    public void sortHeldTiles() {
        //to do once server&client logic is set up
    }

    public void updateScore(int points) {
        score += points;
    }
}
