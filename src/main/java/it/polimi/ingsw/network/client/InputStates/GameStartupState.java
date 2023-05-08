package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.View.CLI.Printer;
import it.polimi.ingsw.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.network.client.InputStatePlayer;
import it.polimi.ingsw.network.client.SocketClient;

public class GameStartupState extends InputState {
    public GameStartupState(InputStatePlayer player) {
        super(player);
    }

    @Override
    public void play() {
        while (BoardRepresentation.getInstance().getBoard() == null) {
            synchronized (BoardRepresentation.getInstance()) {
                waitForVM(BoardRepresentation.getInstance());
            }
        }

        while (ShelvesRepresentation.getInstance().getShelfMessage(SocketClient.getInstance().getNickname()) == null) {
            synchronized (ShelvesRepresentation.getInstance()) {
                waitForVM(ShelvesRepresentation.getInstance());
            }
        }

        while (CommonGoalsRepresentation.getInstance().getCommonGoalMessage() == null){
            synchronized (CommonGoalsRepresentation.getInstance()){
                waitForVM(CommonGoalsRepresentation.getInstance());
            }
        }

        //all elements are ready

        Printer.printHomeScreen();

        //now that the game startup menu is printed, the game can start. Each player gets their state assigned
        if (GameRepresentation.getInstance().getActivePlayerNickname().equals(SocketClient.getInstance().getNickname())){
            player.setState(new ActivePlayerState(player));
        }
        else player.setState(new WaitingPlayerState(player));
    }
}
