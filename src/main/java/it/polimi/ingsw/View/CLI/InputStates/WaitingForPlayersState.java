package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;

/**
 * Player is waiting for other players to join and for the game to start
 */
public class WaitingForPlayersState extends InputState implements VirtualModelObserver {

    /**
     * Waits for the game to start
     */
    @Override
    public void play() {

        if (GameRepresentation.getInstance().getGameMessage().getActivePlayerNickname() == null) {
            System.out.println("Players connected: " + PlayersRepresentation.getInstance().getPlayersList());
            GameRepresentation.getInstance().registerObserver(this);
        } else {
            System.out.println("The game has started!");
            new GameStartupState().play();
        }
    }

    /**
     * Each time a player connects, the user is sent a list of players connected
     */
    @Override
    public void update() {
        if (GameRepresentation.getInstance().getGameMessage().getActivePlayerNickname() == null) {
            System.out.println("Players connected: " + PlayersRepresentation.getInstance().getPlayersList());
        } else {
            System.out.println("The game has started!");
            GameRepresentation.getInstance().removeObserver(this);
            new GameStartupState().play();
        }
    }
}
