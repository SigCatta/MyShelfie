package it.polimi.ingsw;

import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.View.GUI.GuiTest;
import it.polimi.ingsw.View.GUI.SceneController.VirtualModelObservers.ErrorObserver;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.application.Application;

/**
 * Main of the client app.
 */
public class ClientApp {

    public static void main(String[] args) {
        Client client;
        boolean cliParam = false;

        for (String arg : args) {
            if (arg.equals("--cli") || arg.equals("-c")) {
                cliParam = true;
                break;
            }
        }

        String address = "localhost";
//        System.out.println("Insert server IP address:");
//        address = Reader.getInput();
//        if (address.equals("")) address = "localhost";
        try {
            client = SocketClient.getInstance(address, 28888);
            client.readCommand(); // Starts an asynchronous reading from the server.
        } catch (Exception e) {
            System.out.println("could not connect to server");
            System.exit(1);
        }

        if (cliParam) {
            new Thread(InputStatePlayer.getInstance()).start();
        } else {
            Application.launch(GuiTest.class);
        }
    }


}