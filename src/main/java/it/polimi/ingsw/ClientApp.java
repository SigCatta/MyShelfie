package it.polimi.ingsw;

import it.polimi.ingsw.View.CLI.InputStates.NicknameState;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.View.GUI.Gui;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.application.Application;

/**
 * Main of the client app.
 */
public class ClientApp {

    public static void main(String[] args) {
        boolean cliParam = false;

        for (String arg : args) {
            if (arg.equals("--cli") || arg.equals("-c")) {
                cliParam = true;
                break;
            }
        }

        if (cliParam) {
            askForIP();
            new NicknameState().play();
        } else {
            Application.launch(Gui.class);
        }
    }

    private static void askForIP() {
        Client client;

        System.out.println("Insert server IP address:");
        String address = Reader.getInput();
        if (address.equals("")) address = "localhost";

        try {
            client = SocketClient.getInstance(address, 28888);
            client.readCommand();
        } catch (Exception e) {
            System.out.println("could not connect to server");
            System.exit(1);
        }
    }


}