package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.VirtualModel.BoardRepresentation;
import it.polimi.ingsw.VirtualModel.CommonGoalsRepresentation;
import it.polimi.ingsw.VirtualModel.GameRepresentation;
import it.polimi.ingsw.VirtualModel.ShelvesRepresentation;
import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.network.client.SocketClient;

public class GameStartupState extends InputState {
    public GameStartupState(InputStatePlayer player) {
        super(player);
    }

    /**
     * Waits for the VM to update with the model data (representations != null),
     * prints the startup menu when the virtual model is ready.
     * After printing the menu the state is updated depending on the active player
     */
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

        while (CommonGoalsRepresentation.getInstance().getCommonGoalMessage() == null) {
            synchronized (CommonGoalsRepresentation.getInstance()) {
                waitForVM(CommonGoalsRepresentation.getInstance());
            }
        }

        //all elements are ready
        Printer.clearConsole();
        Printer.printHomeScreen();

        //now that the game startup menu is printed, the game can start. Each player gets their state assigned
        if (GameRepresentation.getInstance().getActivePlayerNickname().equals(SocketClient.getInstance().getNickname())) {
            player.setState(new ActivePlayerState(player));
        } else player.setState(new WaitingPlayerState(player));
    }
}
