package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Server.Executor.ChatExecutor;
import it.polimi.ingsw.network.client.SocketClient;

import java.io.Serializable;

public class ChatMTS extends MessageToServer implements Serializable {

    private final String chatMessage;
    private final String sender;

    public ChatMTS(String chatMessage) {
        this.chatMessage = chatMessage;
        this.sender = SocketClient.getInstance().getNickname();
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
