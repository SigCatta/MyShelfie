package it.polimi.ingsw.Controller.server.Messages;

import it.polimi.ingsw.VirtualView.Messages.PlayerMTC;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PlayerMTCTest {
    @Test
    public void dimension() throws IOException {

        PlayerMTC obj = new PlayerMTC(new Player("mario")); // create an instance of your object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj); // serialize the object
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray(); // get the byte array
        int size = bytes.length; // get the length of the byte array
        System.out.println("Serialized player message size: " + size + " bytes");
    }
}
