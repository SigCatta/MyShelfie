package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.network.client.InputStatePlayer;

public class ActivePlayerState extends InputState{
    ActivePlayerState(InputStatePlayer player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("You are the active player!");

        try { //TODO so it doesn't keep printing...
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
