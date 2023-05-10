package it.polimi.ingsw.VirtualView.Messages;

import it.polimi.ingsw.Enum.EchoID;
import it.polimi.ingsw.VirtualModel.EchosRepresentation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class EchoMTC implements MessageToClient, Serializable {
    private final boolean errorFlag;
    private final EchoID id;

    public EchoMTC(EchoID id, boolean errorFlag) {
        this.errorFlag = errorFlag;
        this.id = id;
    }

    @Override
    public void update() {
        EchosRepresentation.getInstance().putMessage(this);
    }

    public boolean isError() {
        return errorFlag;
    }

    public String getOutput() {

        JSONParser parser = new JSONParser();
        InputStream inputSream = this.getClass().getClassLoader().getResourceAsStream("data/echo_messages/ID_map.json");
        assert inputSream != null;
        InputStreamReader reader = new InputStreamReader(inputSream);
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            return (String) jsonObject.get(id.name());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public EchoID getID() {
        return id;
    }
}
