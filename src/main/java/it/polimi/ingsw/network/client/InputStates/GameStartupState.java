package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.View.CLI.BoardView;
import it.polimi.ingsw.View.CLI.CommonGoalView;
import it.polimi.ingsw.View.CLI.ShelfView;
import it.polimi.ingsw.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.network.client.InputReader;
import it.polimi.ingsw.network.client.SocketClient;

import java.util.ArrayList;

public class GameStartupState extends InputState {
    public GameStartupState(InputReader reader) {
        super(reader);
    }

    @Override
    public void play() {
        ArrayList<String> output = new ArrayList<>();

        while (BoardRepresentation.getInstance().getBoard() == null) {
            synchronized (BoardRepresentation.getInstance()) {
                waitForVM(BoardRepresentation.getInstance());
            }
        }
        output = new BoardView().getPrint(output);

        while (ShelvesRepresentation.getInstance().getShelfMessage(SocketClient.getInstance().getNickname()) == null) {
            synchronized (ShelvesRepresentation.getInstance()) {
                waitForVM(ShelvesRepresentation.getInstance());
            }
        }
        output = new ShelfView().getPrint(output);

        while (CommonGoalsRepresentation.getInstance().getCommonGoalMessage() == null){
            synchronized (CommonGoalsRepresentation.getInstance()){
                waitForVM(CommonGoalsRepresentation.getInstance());
            }
        }
        output = new CommonGoalView().getPrint(output);

        output.forEach(System.out::println);
        try { //TODO so it doesn't keep printing...
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
