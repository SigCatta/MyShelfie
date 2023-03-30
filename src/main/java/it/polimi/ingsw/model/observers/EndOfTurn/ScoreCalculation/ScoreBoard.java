package it.polimi.ingsw.model.observers.EndOfTurn.ScoreCalculation;

import it.polimi.ingsw.model.observers.EndOfTurn.EndOfTurnObserver;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreBoard implements EndOfTurnObserver {

    ArrayList<Player> players;
    HashMap<Player, Integer> pointsPerPlayer;

    public ScoreBoard(Game game){
        this.players = game.getPlayers();

        game.getTurnHandler().attachEndOfTurn(this);
    }

    public void updateScore(){
        //TODO update the score for the current player
    }

    @Override
    public void update() {
        updateScore();
    }

    public void setFirstPlayerToFinish(Player activePlayer) {
        //TODO: assign 1 point to the player for being the first one to complete his shelf
    }
}
