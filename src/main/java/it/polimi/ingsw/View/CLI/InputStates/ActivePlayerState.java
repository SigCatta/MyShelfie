package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.network.client.SocketClient;

public class ActivePlayerState extends InputState {
    private final Reader reader;

    ActivePlayerState(InputStatePlayer player, Reader reader) {
        super(player);
        this.reader = reader;
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

        String nickname = SocketClient.getInstance().getNickname();


        while (GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)) { // waits for the model to change and updates the view
            runInputReaderUntilModelUpdate(reader); //TODO use echos
        }

        player.setState(new WaitingPlayerState(player, reader));
    }
}
