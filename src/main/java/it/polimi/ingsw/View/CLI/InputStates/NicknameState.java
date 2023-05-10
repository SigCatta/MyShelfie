package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.network.client.SocketClient;

public class NicknameState extends InputState {

    public NicknameState(InputStatePlayer player) {
        super(player);
    }

    /**
     * Asks the player for a username and checks with the server if it's available,
     * if the name is not available, the user is asked again, changes state otherwise
     */
    @Override
    public void play() {
        System.out.println("Insert nickname:");
        getInput();
        socketClient.sendCommand(new HandshakeMTS(input));

        synchronized (EchosRepresentation.getInstance()){
            waitForVM(EchosRepresentation.getInstance());
        }

        EchoMTC message = EchosRepresentation.getInstance().getMessage();
        if (message.isError()) {
            System.out.println(message.getOutput());
        } else {
            System.out.println(message.getOutput());
            SocketClient.getInstance().setNickname(input);
            input = null;
            player.setState(new StartOrJoinState(player));
        }
    }
}