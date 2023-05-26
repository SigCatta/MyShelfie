package it.polimi.ingsw.View.GUI.SceneController;

import it.polimi.ingsw.VirtualView.Messages.EchoMTC;

/**
 * The GuiController class is an abstract class that serves as the base class for all GUI scene controllers.
 * It defines methods for updating different aspects of the GUI based on the virtual model updates.
 */
public abstract class GuiController {
    /**
     * Updates the game state in the GUI.
     */
    public void updateGame() {
    }

    /**
     * Updates the players' information in the GUI.
     */
    public void updatePlayers() {
    }

    /**
     * Updates the echo message received from the server in the GUI.
     * @param echoMessage the echo message
     */
    public void updateEcho(EchoMTC echoMessage) {
    }

    /**
     * Updates the game board in the GUI.
     */
    public void updateBoard() {
    }

    /**
     * Updates the player's shelf in the GUI.
     */
    public void updateShelf() {
    }

    /**
     * Updates the chosen tiles table in the GUI.
     */
    public void updateChosenTilesTable() {
    }

    /**
     * Updates the chat messages in the GUI.
     */
    public void updateChat() {
    }

    /**
     * Updates the common goals in the GUI.
     */
    public void updateCommonGoals() {
    }

}
