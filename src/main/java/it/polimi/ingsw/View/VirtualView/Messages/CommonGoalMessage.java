package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.ClientController;

import java.io.Serializable;
import java.util.ArrayList;

public class CommonGoalMessage implements MessageToClient, Serializable {
    private final ArrayList<String> drawing;
    private final String description;
    private final int availablePoints;
    private final int commonGoalNumber;

    public CommonGoalMessage(ArrayList<String> drawing, String description, int availablePoints, int commonGoalNumber) { //ideally used only once
        this.drawing = drawing;
        this.description = description;
        this.availablePoints = availablePoints;
        this.commonGoalNumber = commonGoalNumber;
    }

    public CommonGoalMessage(CommonGoalMessage oldCGVersion, int availablePoints) { // used for available points update
        this.drawing = oldCGVersion.getDrawing();
        this.description = oldCGVersion.getDescription();
        this.commonGoalNumber = oldCGVersion.getCommonGoalNumber();
        this.availablePoints = availablePoints; // the only thing that changes
    }

    public int getCommonGoalNumber() {
        return commonGoalNumber;
    }

    public ArrayList<String> getDrawing() {
        return drawing;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    @Override
    public void update() {
        ClientController.getInstance().changeCommonGoal(this);
    }
}
