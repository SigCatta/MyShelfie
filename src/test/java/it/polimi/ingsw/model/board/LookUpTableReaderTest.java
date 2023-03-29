package it.polimi.ingsw.model.board;

import it.polimi.ingsw.JSONReader.LookUpTableReader;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class LookUpTableReaderTest {
    LookUpTableReader lookUpTableReader = new LookUpTableReader();

    @Test
    public void test2() {

        boolean[][] expectedTable = {
                {false, false, false, false, false, false, false, false, false},
                {false, false, false, true , true , false, false, false, false},
                {false, false, false, true , true , true , true , false, false},
                {false, false, true , true , true , true , true , true , false},
                {false, true , true , true , true , true , true , true , false},
                {false, true , true , true , true , true , true , false, false},
                {false, false, false, true , true , true , false, false, false},
                {false, false, false, false, true , true , false, false, false},
                {false, false, false, false, false, false, false, false, false}
        };

        boolean[][] gottenTable = lookUpTableReader.getLookUpTable(2);

        for(int i = 0; i < expectedTable.length; i++){
            for(int j = 0; j < expectedTable.length; j++){
                assertEquals(gottenTable[i][j], expectedTable[i][j]);
            }
        }

    }

    @Test
    public void test3() {

        boolean[][] expectedTable = {
                {false, false, false, true , false, false, false, false, false},
                {false, false, false, true , true , false, false, false, false},
                {false, false, true , true , true , true , true , false, false},
                {false, false, true , true , true , true , true , true , false},
                {false, true , true , true , true , true , true , true , false},
                {true , true , true , true , true , true , true , false, false},
                {false, false, true , true , true , true , true , false, false},
                {false, false, false, false, true , true , false, false, false},
                {false, false, false, false, false, true , false, false, false}
        };

        boolean[][] gottenTable = lookUpTableReader.getLookUpTable(3);

        for(int i = 0; i < expectedTable.length; i++){
            for(int j = 0; j < expectedTable.length; j++){
                assertEquals(gottenTable[i][j], expectedTable[i][j]);
            }
        }

    }

    @Test
    public void test4() {

        boolean[][] expectedTable = {
                {false, false, false, true , true , false, false, false, false},
                {false, false, false, true , true , true, false, false, false},
                {false, false, true , true , true , true , true , false, false},
                {false, true , true , true , true , true , true , true , false},
                {true , true , true , true , true , true , true , true , true },
                {true , true , true , true , true , true , true , true , false},
                {false, false, true , true , true , true , true , false, false},
                {false, false, false, true , true , true , false, false, false},
                {false, false, false, false, true , true , false, false, false}
        };

        boolean[][] gottenTable = lookUpTableReader.getLookUpTable(4);

        for(int i = 0; i < expectedTable.length; i++){
            for(int j = 0; j < expectedTable.length; j++){
                assertEquals(gottenTable[i][j], expectedTable[i][j]);
            }
        }
    }

}
