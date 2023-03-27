package it.polimi.ingsw.model.board.ScoreCalculation;

import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreBoard {

    ArrayList<Player> players;
    HashMap<Player, Integer> pointsPerPlayer;

    public ScoreBoard(ArrayList<Player> players){
        this.players = players;
    }



}
