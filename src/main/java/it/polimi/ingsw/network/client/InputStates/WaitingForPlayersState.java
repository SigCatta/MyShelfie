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
           synchronized (GameRepresentation.getInstance()){
               waitForVM(GameRepresentation.getInstance());
           }
        }
        reader.setState(new GameStartupState(reader));
        System.out.println("The game has started!");
    }
}
