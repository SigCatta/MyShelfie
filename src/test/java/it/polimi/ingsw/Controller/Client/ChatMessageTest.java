package it.polimi.ingsw.Controller.Client;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ChatMessageTest {
    @Test
    public void dimension() throws IOException {

        MessageToServer obj = new ChatMTS("Nemo");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj); // serialize the object
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray(); // get the byte array
        int size = bytes.length; // get the length of the byte array
        System.out.println("Serialized chat message size: " + size + " bytes");
    }
}
