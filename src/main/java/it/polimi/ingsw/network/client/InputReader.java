package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.client.InputStates.InputState;
import it.polimi.ingsw.network.client.InputStates.NicknameState;

public class InputReader implements Runnable {
    private InputState state;

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        state = new NicknameState(this);
        while (true) {
            state.play();
        }
    }

    public void setState(InputState state) {
        this.state = state;
    }

}
