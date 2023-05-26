package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Server.Executor.ChatExecutor;
import it.polimi.ingsw.network.client.SocketClient;

import java.io.Serializable;

/**
 * Posts a message to the public chat
 */
public class ChatMTS extends MessageToServer implements Serializable {

    private final String chatMessage;
    private final String sender;
    private final String receiver;

    public ChatMTS(String chatMessage, String receiver) {
        this.chatMessage = chatMessage;
        this.receiver = receiver;
        this.sender = SocketClient.getInstance().getNickname();
    }

    public String getReceiver() {
        return receiver;
    }

    @Override
    public void update() {
        ChatExecutor.execute(this);
    }

    public String getSender() {
        return sender;
    }

    public String getChatMessage() {
        return chatMessage;
    }
}
