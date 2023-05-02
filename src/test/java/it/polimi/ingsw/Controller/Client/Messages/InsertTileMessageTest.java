package it.polimi.ingsw.Controller.Client.Messages;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class InsertTileMessageTest {
    @Test
    public void dimension() throws IOException {

        MessageToServer obj = new InsertTileMessage(1, 1);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj); // serialize the object
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray(); // get the byte array
        int size = bytes.length; // get the length of the byte array
        System.out.println("Serialized insert tiles message size: " + size + " bytes");
    }
}
