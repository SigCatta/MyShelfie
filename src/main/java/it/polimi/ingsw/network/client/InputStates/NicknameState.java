package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.Controller.Client.Messages.HandshakeMessage;
import it.polimi.ingsw.Controller.Client.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.View.VirtualView.Messages.EchoToClient;
import it.polimi.ingsw.network.client.InputReader;
import it.polimi.ingsw.network.client.SocketClient;

public class NicknameState extends InputState {

    public NicknameState(InputReader reader) {
        super(reader);
    }

    @Override
    public void play() {
        System.out.println("Insert nickname:");
        getInput();
        socketClient.sendCommand(new HandshakeMessage(input));
        try {
            synchronized (EchosRepresentation.getInstance()) {
                EchosRepresentation.getInstance().wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        EchoToClient message = EchosRepresentation.getInstance().getMessage();
        if (message.isError()) {
            System.out.println(message.getOutput());
        } else {
            System.out.println(message.getOutput());
            SocketClient.getInstance().setNickname(input);
            input = null;
            reader.setState(new StartOrJoinState(reader));
        }
    }
}
