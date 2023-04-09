package it.polimi.ingsw;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;

import java.io.IOException;
import java.util.Map;

import static it.polimi.ingsw.InputReader.askNickname;
import static it.polimi.ingsw.InputReader.askServerInfo;

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

        String nickname = askNickname();
        Map<String, String> serverInfo = askServerInfo();
        try {
            client = new SocketClient(serverInfo.get("address"), Integer.parseInt(serverInfo.get("port")));
            client.setNickname(nickname);
            client.readCommand(); // Starts an asynchronous reading from the server.
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("errore");
            //TODO
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