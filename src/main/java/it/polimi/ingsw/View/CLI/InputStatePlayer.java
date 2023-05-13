package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.View.CLI.InputStates.InputState;
import it.polimi.ingsw.View.CLI.InputStates.NicknameState;

public class InputStatePlayer implements Runnable {
    private InputState state;

    private static InputStatePlayer instance;

    /**
     * Plaus the state which the player is, the starting state is NicknameState
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        state = new NicknameState();
        while (true) {
            state.play();
        }
    }

    private InputStatePlayer() {
    }

    public static InputStatePlayer getInstance() {
        if (instance == null) instance = new InputStatePlayer();
        return instance;
    }

    /**
     * Stets the state
     *
     * @param state the state to set
     */
    public void setState(InputState state) {
        this.state = state;
    }

    public InputState getState() {
        return state;
    }
}
