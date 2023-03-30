package it.polimi.ingsw.model.EndOfTurn.ScoreCalculation;

import it.polimi.ingsw.model.EndOfTurn.EndOfTurnObserver;
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
}
