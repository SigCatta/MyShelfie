package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.network.client.InputStatePlayer;
import it.polimi.ingsw.network.client.InputStates.readers.WaitingReader;
import it.polimi.ingsw.network.client.SocketClient;

public class WaitingPlayerState extends InputState {
    WaitingPlayerState(InputStatePlayer player) {
        super(player);
    }

    /**
     * Tells the user he is NOT the active player, starts a thread to read any eventual player commands
     * and waits for any changes in the model, if the player is using a command, the view will update
     * after the command has been executed.<br>
     * If the ative plauer's turn end and the user is not using any commands, its state will switch to active
     */
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
