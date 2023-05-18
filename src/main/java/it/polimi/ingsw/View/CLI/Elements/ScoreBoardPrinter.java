package it.polimi.ingsw.View.CLI.Elements;

import it.polimi.ingsw.Enum.GameState;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;

public class ScoreBoardPrinter implements VirtualModelObserver {
    private static ScoreBoardPrinter instance;
    private boolean hasPrinted;

    private ScoreBoardPrinter (){
        hasPrinted = false;
        PlayersRepresentation.getInstance().registerObserver(this);
    }

    public static ScoreBoardPrinter getInstance(){
        if (instance == null) instance = new ScoreBoardPrinter();
        return instance;
    }
    public boolean hasPrinted() {
        return hasPrinted;
    }

    /**
     * Prints the leaderboard
     */
    @Override
    public void update() {
        if (GameRepresentation.getInstance().getGameState() != GameState.END) return;

        ArrayList<PlayerMTC> players = PlayersRepresentation.getInstance().getAllPlayerMTC();
        players.sort((a, b) -> b.getScore() - a.getScore());

        System.out.println("\n");
        for (int i = 0; i < players.size(); i++) {
            PlayerMTC player = players.get(i);
            if (player.getNickname().equals(SocketClient.getInstance().getNickname())) {
                System.out.println("\u001B[1m" + (i + 1) + ". " + player.getNickname() + "     " + player.getScore() + "\033[0m");
            } else {
                System.out.println((i + 1) + ". " + player.getNickname() + "     " + player.getScore());
            }
        }

        hasPrinted = true;
        synchronized (this){
            notifyAll();
        }
    }
}
