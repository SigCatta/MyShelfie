package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.ClientController.Controller;

public class ChatMessage implements Message{
    @Override
    public void update() {
        Controller.getInstance().chat(this);
    }
}
