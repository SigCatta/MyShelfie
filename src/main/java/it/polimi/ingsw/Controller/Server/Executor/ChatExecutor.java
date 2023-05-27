package it.polimi.ingsw.Controller.Server.Executor;

import it.polimi.ingsw.Controller.Client.ChatMTS;
import it.polimi.ingsw.Controller.Client.MessageToServer;
import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.VirtualView.Messages.ChatMTC;
import it.polimi.ingsw.VirtualView.Messages.EchoMTC;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.server.SocketClientHandler;

/**
 * This class is responsible for executing the {@link ChatMTS} command received from the client.
 * It handles broadcasting messages or sending messages to specific recipients.
 */
public class ChatExecutor implements Executor {

    public static void execute(MessageToServer message) {
        Game game = message.getGame();
        ChatMTS chatMessage = (ChatMTS) message;
        if (chatMessage.getReceiver() == null) { // broadcast message
            game.getVirtualView().send(new ChatMTC(chatMessage.getChatMessage(), chatMessage.getSender(), null, true));
        } else {
            SocketClientHandler clientHandler = game.getVirtualView().getSocketHandlerClientByNickname(chatMessage.getReceiver());
            try {
                clientHandler.sendCommand(new ChatMTC(chatMessage.getChatMessage(), chatMessage.getSender(), chatMessage.getReceiver(), false));
                message.getSocketClientHandler().sendCommand(new ChatMTC(chatMessage.getChatMessage(), chatMessage.getSender(), chatMessage.getReceiver(), false));
            } catch (NullPointerException e) {
                message.getSocketClientHandler().sendCommand(new EchoMTC(EchoID.NOPLAYER, true));
            }
        }
    }
}
