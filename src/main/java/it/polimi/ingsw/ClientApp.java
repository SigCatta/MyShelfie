package it.polimi.ingsw;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;

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

        //TODO : implement when Cli and Gui will be done
        if (cliParam) {
            //launch cli app
        } else {
            //launch gui app
        }
    }



}