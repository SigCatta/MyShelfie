package it.polimi.ingsw.model.board.ScoreCalculation;

import it.polimi.ingsw.model.Game.EndOfTurnObserver;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreBoard implements EndOfTurnObserver {

    ArrayList<Player> players;
    HashMap<Player, Integer> pointsPerPlayer;

    public ScoreBoard(ArrayList<Player> players){
        this.players = players;
    }

    public void updateScore(){
        //TODO update the score for the current player
    }

    @Override
    public void update() {
        updateScore();
    }
}
