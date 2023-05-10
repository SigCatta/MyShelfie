package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.View.CLI.Elements.ChatView;
import it.polimi.ingsw.VirtualModel.ChatRepresentation;
import it.polimi.ingsw.VirtualView.Messages.ChatMTC;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ChatViewTest {
    @Test
    public void getPrintTest1(){
        new ChatView().getPrint(new ArrayList<>()).forEach(System.out::println); // shouldn't print anything
    }
    @Test
    public void getPrintTest2(){
        ChatMTC message = new ChatMTC("messaggio test", "User");
        ChatRepresentation.getInstance().addMessage(message);

        new ChatView().getPrint(new ArrayList<>()).forEach(System.out::println);
    }
}
