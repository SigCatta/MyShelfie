package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.View.CLI.InputStatePlayer;

public class WaitingForPlayersState extends InputState {

    /**
     * The player waits for the game to start
     */
    @Override
    public void play() {
        while (GameRepresentation.getInstance().getGameMessage().getActivePlayerNickname() == null) {
            System.out.println("Players connected: " + PlayersRepresentation.getInstance().getPlayersList());
            synchronized (GameRepresentation.getInstance()) {
                waitForVM(GameRepresentation.getInstance());
            }
        }

        InputStatePlayer.getInstance().setState(new GameStartupState());
        System.out.println("The game has started!");
    }
}
