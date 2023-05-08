package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.InputStatePlayer;
import it.polimi.ingsw.network.client.SocketClient;

public class NicknameState extends InputState {

    public NicknameState(InputStatePlayer player) {
        super(player);
    }

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
