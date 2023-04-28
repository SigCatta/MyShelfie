package it.polimi.ingsw.Controller.server.Messages;

import it.polimi.ingsw.View.VirtualView.Messages.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ErrorMessageTest {
    @Test
    public void dimension() throws IOException {
        String errorMessage = "not Hello world!";

        ErrorMessage obj = new ErrorMessage(errorMessage); // create an instance of your object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj); // serialize the object
        oos.flush();
        oos.close();
        byte[] bytes = bos.toByteArray(); // get the byte array
        int size = bytes.length; // get the length of the byte array
        System.out.println("Serialized error size: " + size + " bytes");
    }
}
