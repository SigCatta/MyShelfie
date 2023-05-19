package it.polimi.ingsw.View.CLI.Elements;

import it.polimi.ingsw.VirtualModel.PlayersRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;

import java.util.ArrayList;

public class DisconnectionHandler implements VirtualModelObserver {

    private static DisconnectionHandler instance;

    private DisconnectionHandler() {
        PlayersRepresentation.getInstance().registerObserver(this);
    }

    public static DisconnectionHandler getInstance() {
        if (instance == null) instance = new DisconnectionHandler();
        return instance;
    }

    @Override
    public void update() {
        ArrayList<PlayerMTC> messages = PlayersRepresentation.getInstance().getAllPlayerMTC();
        for (PlayerMTC p : messages) {
            if (!p.isConnected()) handleDisconnection();
        }
    }

    private void handleDisconnection() {
        System.out.println("A player disconnected, therefore the game has terminated.");
        System.exit(1);
    }


}
