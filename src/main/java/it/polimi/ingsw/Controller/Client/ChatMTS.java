package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.Controller.Server.Executor.ChatExecutor;

import java.io.Serializable;

public class ChatMTS extends MessageToServer implements Serializable {

    private String chatMessage;

    public ChatMTS(String chatMessage){
        this.chatMessage = chatMessage;
    }

    @Override
    public void update() {
        ChatExecutor.execute(this);
    }

    public String getChatMessage() {
        return chatMessage;
    }
}
