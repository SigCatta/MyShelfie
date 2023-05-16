package it.polimi.ingsw;

import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.View.GUI.GuiTest;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;
import javafx.application.Application;

/**
 * Main of the client app.
 */
public class ClientApp {

    public static void main(String[] args) {
        Client client;
        boolean cliParam = false; // default value

        for (String arg : args) {
            if (arg.equals("--cli") || arg.equals("-c")) {
                cliParam = true;
                break;
            }
        }

        //String nickname = askNickname();
        //Map<String, String> serverInfo = InputReader.askServerInfo();
        try {
            client = SocketClient.getInstance("localhost", 28888);
            //client = new SocketClient(serverInfo.get("address"), Integer.parseInt(serverInfo.get("port")), nickname);
            client.readCommand(); // Starts an asynchronous reading from the server.
        } catch (Exception e) {
            System.out.println("could not connect to server");
            return;
        }

        //TODO : implement when Cli and GUI will be done
        if (cliParam) {
            new Thread(InputStatePlayer.getInstance()).start();
        } else {
            Application.launch(GuiTest.class);
        }
    }



}