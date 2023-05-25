package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputReaderTest {

    @Test
    void readLine() {
        String input = "test\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String line = null;
        try {
            line = InputReader.readLine();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertEquals("test", line);
    }
}