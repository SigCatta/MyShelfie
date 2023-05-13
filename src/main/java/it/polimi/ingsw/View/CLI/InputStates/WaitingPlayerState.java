package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.network.client.SocketClient;

public class WaitingPlayerState extends InputState {
    private final Reader reader;

    WaitingPlayerState(Reader reader) {
        this.reader = reader;
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

        String nickname = SocketClient.getInstance().getNickname();

        while (!GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)) { // waits for the model to change and updates the view
            runInputReaderUntilModelUpdate(reader);
        }

        InputStatePlayer.getInstance().setState(new ActivePlayerState(reader));
    }
}
