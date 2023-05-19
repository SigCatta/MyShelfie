package it.polimi.ingsw.View.CLI;

import it.polimi.ingsw.View.CLI.Elements.Views.ChatView;
import it.polimi.ingsw.VirtualModel.ChatRepresentation;
import it.polimi.ingsw.VirtualView.Messages.ChatMTC;
import it.polimi.ingsw.network.client.Client;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ChatViewTest {
    @Test
    public void getPrintTest1() {
        ChatView.getInstance().getPrint(new ArrayList<>()).forEach(System.out::println); // shouldn't print anything
    }

    @Test
    public void getPrintTest2() {
        ChatMTC message = new ChatMTC("messaggio test", "User", null,true);
        try {
            ChatRepresentation.getInstance().addMessage(message);
        } catch (NullPointerException e) {
            Client.LOGGER.info("The Virtual Model was not ready");
        }

        ChatView.getInstance().getPrint(new ArrayList<>()).forEach(System.out::println);
    }
}
