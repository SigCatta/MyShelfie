package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.network.client.InputStatePlayer;
import it.polimi.ingsw.network.client.InputStates.readers.PlayingReader;
import it.polimi.ingsw.network.client.SocketClient;

public class ActivePlayerState extends InputState {
    ActivePlayerState(InputStatePlayer player) {
        super(player);
    }


    /**
     * Tells the user he is the active player, starts a thread to read any eventual player commands
     * and waits for any changes in the model, if the player is using a command, the view will update
     * after the command has been executed
     * If the player at any points stops being the active player, the state changes to waiting
     */
    @Override
    public void play() {
        System.out.println("You are the active player!");
        PlayingReader reader = new PlayingReader();
        Thread readerThread = new Thread(reader);

        String nickname = SocketClient.getInstance().getNickname();

        readerThread.start(); //reads input commands and sends messages to the server

        while (GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)) { // waits for the model to change and updates the view
            runInputReaderUntilModelUpdate(reader); //TODO use echos
        }

        player.setState(new WaitingPlayerState(player));
    }
}
