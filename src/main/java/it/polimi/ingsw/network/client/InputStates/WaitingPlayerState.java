package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.network.client.InputStatePlayer;

public class WaitingPlayerState extends InputState{
    WaitingPlayerState(InputStatePlayer player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("You are not the active player!");

        try { //TODO so it doesn't keep printing...
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
