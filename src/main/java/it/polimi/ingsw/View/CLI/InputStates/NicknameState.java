package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualModel.VirtualModelObserver;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.SocketClient;

public class NicknameState extends InputState implements VirtualModelObserver {


    /**
     * Asks the player for a username and checks with the server if it's available,
     * if the name is not available, the user is asked again, changes state otherwise
     */
    @Override
    public void play() {
        do {
            System.out.println("Insert nickname:");
            input = Reader.getInput();
            if (input.equals("")) System.out.println("Invalid nickname!");
        } while (input.equals(""));

        socketClient.sendCommand(new HandshakeMTS(input));
        EchosRepresentation.getInstance().registerObserver(this);
    }

    @Override
    public void update() {
        EchoMTC message = EchosRepresentation.getInstance().popMessage();

        System.out.println(message.getOutput());
        if (message.getID().equals(EchoID.NICKOK)) {
            SocketClient.getInstance().setNickname(input);
            input = null;
            EchosRepresentation.getInstance().removeObserver(this);
            new StartOrJoinState().play();
        } else if (message.getID().equals(EchoID.BADNICK)) {
            EchosRepresentation.getInstance().removeObserver(this);
            play();
        }
    }
}
