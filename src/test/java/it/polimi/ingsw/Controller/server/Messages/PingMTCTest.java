package it.polimi.ingsw.Controller.server.Messages;

import it.polimi.ingsw.VirtualView.Messages.PingMTC;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PingMTCTest {
    @Test
    public void dimension() throws IOException {

        PingMTC obj = new PingMTC(); // create an instance of your object
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
