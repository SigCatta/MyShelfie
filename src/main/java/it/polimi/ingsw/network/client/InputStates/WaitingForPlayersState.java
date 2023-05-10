package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.InputStatePlayer;

public class WaitingForPlayersState extends InputState {
    public WaitingForPlayersState(InputStatePlayer player) {
        super(player);
    }

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
        player.setState(new GameStartupState(player));
        System.out.println("The game has started!");
    }
}
