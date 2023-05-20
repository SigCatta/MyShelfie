package it.polimi.ingsw;

import it.polimi.ingsw.View.CLI.InputStates.NicknameState;
import it.polimi.ingsw.View.GUI.Gui;
import it.polimi.ingsw.network.client.Client;
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
//
//        String address = "localhost";
//
//        try {
//            client = SocketClient.getInstance(address, 28888);
//            client.readCommand(); // Starts an asynchronous reading from the server.
//        } catch (Exception e) {
//            System.out.println("could not connect to server");
//            System.exit(1);
//        }

        if (cliParam) {
            new NicknameState().play();
        } else {
            Application.launch(Gui.class);
        }
    }


}