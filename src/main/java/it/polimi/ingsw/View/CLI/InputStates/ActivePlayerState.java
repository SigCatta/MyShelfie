package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * Player is active
 */
public class ActivePlayerState extends InputState implements VirtualModelObserver {
    private final Reader reader;

    public ActivePlayerState(Reader reader) {
        this.reader = reader;
    }


    /**
     * Tells the user he is the active player, registers to {@link GameRepresentation}
     * observers to be notified when the turn ends
     */
    @Override
    public void play() {
        System.out.println("You are the active player!");
        GameRepresentation.getInstance().registerObserver(this);
    }


    /**
     * If the turn ends and the user is not using any commands, its state will switch to waiting
     */
    @Override
    public void update() {
        String nickname = socketClient.getNickname();

        if (!GameRepresentation.getInstance().getActivePlayerNickname().equals(nickname)) {
            GameRepresentation.getInstance().removeObserver(this);
            new WaitingPlayerState(reader).play();
        }
    }
}
