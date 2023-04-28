package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Controller.Commands.CommandMapKey;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandTest {
    @Test
    public void printEnum(){
        System.out.println(CommandMapKey.COMMAND);
        assertEquals("COMMAND", String.valueOf(CommandMapKey.COMMAND));
    }
}
