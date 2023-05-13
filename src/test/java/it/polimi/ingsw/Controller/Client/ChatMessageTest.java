package it.polimi.ingsw.Controller.Client;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.SocketClient;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ChatMessageTest {
    @Test
    public void dimension() throws IOException {
        try {
            SocketClient.getInstance().setNickname("User");
            MessageToServer obj = new ChatMTS("Nemo");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj); // serialize the object
            oos.flush();
            oos.close();
            byte[] bytes = bos.toByteArray(); // get the byte array
            int size = bytes.length; // get the length of the byte array
            System.out.println("Serialized chat message size: " + size + " bytes");
        } catch (RuntimeException e) {
            Client.LOGGER.severe("The server is not running");
            /*
                Creating a ChatMTS requires an instance of SocketClient,
                you need a server running to create a new one since the constructor
                throws a RuntimeException if the connection to the server fails
             */
        }
    }
}
