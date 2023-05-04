package it.polimi.ingsw.network.client.InputStates;

import it.polimi.ingsw.Controller.Client.Messages.HandshakeMessage;
import it.polimi.ingsw.Controller.Client.VirtualModel.ErrorsRepresentation;
import it.polimi.ingsw.Controller.Client.VirtualModel.GeneralMessagesRepresentation;
import it.polimi.ingsw.Enum.ErrorCode;
import it.polimi.ingsw.network.client.InputReader;
import it.polimi.ingsw.network.client.SocketClient;

public class NicknameState extends InputState {

    public NicknameState(InputReader reader){
        super(reader);
    }
    @Override
    public void play() {
        while (true) {
            System.out.println("Insert nickname:");
            getInput();
            socketClient.sendCommand(new HandshakeMessage(input));
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (ErrorsRepresentation.getInstance().getErrorCodes().contains(ErrorCode.BADNICK)) {
                    ErrorsRepresentation.getInstance().removeError(ErrorCode.BADNICK);
                    System.out.println("This nickname is already taken");
                    break;
                }
                if (GeneralMessagesRepresentation.getInstance().getCodes().contains("NICKOK")) {
                    SocketClient.getInstance().setNickname(input);
                    System.out.println("Nickname " + SocketClient.getInstance().getNickname() + " accepted!");
                    input = null;
                    reader.setState(new StartOrJoinState(reader));
                    return;
                }
            }
        }
    }
}
