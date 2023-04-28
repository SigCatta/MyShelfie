package it.polimi.ingsw.Controller.server.Messages;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class HashMapMessageTest {
    @Test
    public void dimension() throws IOException {
        HashMap<String, String> message = new HashMap<>();

        message.put("COMMAND", "NEW_GAME");
        message.put("NICKNAME", "OMAR");
        message.put("GAMEID", "12312");
        message.put("A", "12312");
        message.put("C", "12312");
        message.put("V", "12312");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(message); // serialize the object
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray(); // get the byte array
        int size = bytes.length; // get the length of the byte array
        System.out.println("Serialized hash map size: " + size + " bytes");
    }
}
