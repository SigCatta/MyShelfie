package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.View.CLI.BoardView;
import it.polimi.ingsw.network.client.InputReader;

import java.util.ArrayList;

public class GameStartupState extends InputState{
    public GameStartupState(InputReader reader) {
        super(reader);
    }

    @Override
    public void play() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new BoardView().getPrint(new ArrayList<>()).forEach(System.out::println);
        try {
            synchronized (this){
                this.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
