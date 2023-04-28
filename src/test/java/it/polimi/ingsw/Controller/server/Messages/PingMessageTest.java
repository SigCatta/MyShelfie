package it.polimi.ingsw.Controller.server.Messages;

import it.polimi.ingsw.View.VirtualView.Messages.GameMessage;
import it.polimi.ingsw.View.VirtualView.Messages.PingMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PingMessageTest {
    @Test
    public void dimension() throws IOException {

        PingMessage obj = new PingMessage(); // create an instance of your object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj); // serialize the object
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray(); // get the byte array
        int size = bytes.length; // get the length of the byte array
        System.out.println("Serialized ping size: " + size + " bytes");
    }
}
