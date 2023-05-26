package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * Player is not active
 */
public class WaitingPlayerState extends InputState implements VirtualModelObserver {
    private final Reader reader;

    WaitingPlayerState(Reader reader) {
        this.reader = reader;
    }

    /**
     * Tells the user he is NOT the active player, registers to {@link GameRepresentation}
     * observers to be notified when the active player changes
     */
    @Override
    public void play() {
        System.out.println("You are not the active player...");
        GameRepresentation.getInstance().registerObserver(this);
    }

    /**
     * If the active player's turn ends and the user is not using any commands, its state will switch to active
     */
    @Override
    public void update() {
        String nickname = socketClient.getNickname();

        if (GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)) {
            GameRepresentation.getInstance().removeObserver(this);
            new ActivePlayerState(reader).play();
        }

    }
}
