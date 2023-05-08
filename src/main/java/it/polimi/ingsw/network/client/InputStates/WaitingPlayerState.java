package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.network.client.InputStatePlayer;
import it.polimi.ingsw.network.client.InputStates.readers.WaitingReader;
import it.polimi.ingsw.network.client.SocketClient;

public class WaitingPlayerState extends InputState {
    WaitingPlayerState(InputStatePlayer player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("You are not the active player...");
        WaitingReader reader = new WaitingReader();

        Thread readerThread = new Thread(reader);

        String nickname = SocketClient.getInstance().getNickname();

        readerThread.start(); //reads input commands and sends messages to the server

        while (!GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)) { // waits for the model to change and updates the view
            runInputReaderUntilModelUpdate(reader);
        }

        readerThread.interrupt();

        player.setState(new ActivePlayerState(player));
    }
}
