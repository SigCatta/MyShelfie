package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.Controller.Client.VirtualModel.GameRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.network.client.InputReader;

public class WaitingForPlayersState extends InputState {
    public WaitingForPlayersState(InputReader reader) {
        super(reader);
    }

    @Override
    public void play() {
        while (GameRepresentation.getInstance().getGameMessage().getActivePlayerNickname() == null) {
            System.out.println("Players connected: " + PlayersRepresentation.getInstance().getPlayersList());
            try {
                synchronized (GameRepresentation.getInstance()) {
                    GameRepresentation.getInstance().wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        reader.setState(new GameStartupState(reader));
        System.out.println("The game has started!");
    }
}
