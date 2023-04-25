package it.polimi.ingsw.Controller.Client.VirtualModel;

public class VirtualModel {
    private static VirtualModel virtualModelInstance;
    private VirtualModel(){}

    public static VirtualModel getInstance(){
        if(virtualModelInstance == null) virtualModelInstance = new VirtualModel();
        return virtualModelInstance;
    }
}
