package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.View.CLI.Elements.GameStopper;
import it.polimi.ingsw.View.CLI.Elements.Printer;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.*;

public class GameStartupState extends InputState implements VirtualModelObserver {


    /**
     * Waits for the VM to update with the model data (representations != null),
     * prints the startup menu when the virtual model is ready.
     * After printing the menu the state is updated depending on the active player
     */
    @Override
    public void play() {
        BoardRepresentation.getInstance().registerObserver(this);
        ShelvesRepresentation.getInstance().registerObserver(this);
        CommonGoalsRepresentation.getInstance().registerObserver(this);

        update(); // checks if the VM data is ready (the VM won't call update if it's already been updated)
    }

    @Override
    public void update() {
        if (BoardRepresentation.getInstance().getBoard() == null) return;
        if (ShelvesRepresentation.getInstance().getShelfMessage(socketClient.getNickname()) == null) return;
        if (CommonGoalsRepresentation.getInstance().getCommonGoalMessage() == null) return;

        BoardRepresentation.getInstance().removeObserver(this);
        ShelvesRepresentation.getInstance().removeObserver(this);
        CommonGoalsRepresentation.getInstance().removeObserver(this);


        //all elements are ready
        Printer.clearConsole();
        Printer.getInstance().update();

        Reader reader = Reader.getInstance();
        EchosRepresentation.getInstance().registerObserver(reader);
        new Thread(reader).start();

        GameStopper.getIntance();

        //now that the game startup menu is printed, the game can start. Each player gets their state assigned
        if (GameRepresentation.getInstance().getActivePlayerNickname().equals(socketClient.getNickname())) {
            new ActivePlayerState(reader).play();
        } else new WaitingPlayerState(reader).play();
    }
}
