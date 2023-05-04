package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.network.client.InputReader;

public class GameStartupState extends InputState{
    public GameStartupState(InputReader reader) {
        super(reader);
    }

    @Override
    public void play() {
        System.out.println("The game has started!");
    }
}
