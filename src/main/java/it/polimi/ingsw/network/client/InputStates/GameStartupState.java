package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.Controller.Client.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.View.CLI.BoardView;
import it.polimi.ingsw.View.CLI.ShelfView;
import it.polimi.ingsw.network.client.InputReader;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;

public class GameStartupState extends InputState {
    public GameStartupState(InputReader reader) {
        super(reader);
    }

    @Override
    public void play() {
        while (BoardRepresentation.getInstance().getBoard() == null) {
            try {
                synchronized (BoardRepresentation.getInstance()) {
                    BoardRepresentation.getInstance().wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ArrayList<String> output = new ArrayList<>();
        new BoardView().getPrint(output);
        while (ShelvesRepresentation.getInstance().getShelfMessage(SocketClient.getInstance().getNickname()) == null){
            synchronized (ShelvesRepresentation.getInstance()){
                try {
                    ShelvesRepresentation.getInstance().wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        new ShelfView().getPrint(output);
        output.forEach(System.out::println);
        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
