package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;

public abstract class InputState {
    String input;
    final Client socketClient = SocketClient.getInstance();

    public abstract void play();
}
