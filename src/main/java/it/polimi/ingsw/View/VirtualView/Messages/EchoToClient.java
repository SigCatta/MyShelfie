package it.polimi.ingsw.View.VirtualView.Messages;

import it.polimi.ingsw.Controller.Client.VirtualModel.EchosRepresentation;
import it.polimi.ingsw.Enum.EchoID;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class EchoToClient implements MessageToClient, Serializable {
    private final boolean errorFlag;
    private final EchoID id;

    public EchoToClient(EchoID id, boolean errorFlag) {
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
        FileReader fileReader;
        try {
            fileReader = new FileReader("src/data/echo_messages/ID_map.json");
            JSONObject JSONObj = (JSONObject) parser.parse(fileReader);
            return (String) JSONObj.get(id.name());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
