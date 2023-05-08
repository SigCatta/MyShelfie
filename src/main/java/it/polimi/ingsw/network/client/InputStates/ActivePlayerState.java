package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.network.client.InputStatePlayer;
import it.polimi.ingsw.network.client.InputStates.readers.PlayingReader;
import it.polimi.ingsw.network.client.SocketClient;

public class ActivePlayerState extends InputState {
    ActivePlayerState(InputStatePlayer player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("You are the active player!");
        PlayingReader reader = new PlayingReader();
        Thread readerThread = new Thread(reader);

        String nickname = SocketClient.getInstance().getNickname();

        readerThread.start(); //reads input commands and sends messages to the server

        while (GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)) { // waits for the model to change and updates the view
            runInputReaderUntilModelUpdate(reader);
        }

        player.setState(new WaitingPlayerState(player));
    }
}
