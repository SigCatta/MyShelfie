package it.polimi.ingsw.View.CLI.InputStates;

import it.polimi.ingsw.Controller.Client.HandshakeMTS;
import it.polimi.ingsw.View.CLI.InputStatePlayer;
import it.polimi.ingsw.View.CLI.InputStates.reader.Reader;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.network.client.SocketClient;

public class NicknameState extends InputState {


    /**
     * Asks the player for a username and checks with the server if it's available,
     * if the name is not available, the user is asked again, changes state otherwise
     */
    @Override
    public void play() {
        System.out.println("Insert nickname:");
        input = Reader.getInput();
        socketClient.sendCommand(new HandshakeMTS(input));

        synchronized (EchosRepresentation.getInstance()) {
            waitForVM(EchosRepresentation.getInstance());
        }

        EchoMTC message = EchosRepresentation.getInstance().popMessage();

        System.out.println(message.getOutput());
        if (!message.isError()) {
            SocketClient.getInstance().setNickname(input);
            input = null;
            InputStatePlayer.getInstance().setState(new StartOrJoinState());
        }
    }
}
