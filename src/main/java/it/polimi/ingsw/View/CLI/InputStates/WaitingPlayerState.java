package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

public class WaitingPlayerState extends InputState implements VirtualModelObserver {
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
        GameRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        String nickname = socketClient.getNickname();

        if (GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)) {
            GameRepresentation.getInstance().removeObserver(this);
            new ActivePlayerState(reader).play();
        }

    }
}
