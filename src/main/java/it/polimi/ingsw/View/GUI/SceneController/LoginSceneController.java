package it.polimi.ingsw.View.GUI.SceneController;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginSceneController {
    @FXML
    TextArea nicknameField;

    @FXML
    TextArea ipField;

    @FXML
    Group continueLoginBtn;

    @FXML
    Group goBackLoginBtn;

    @FXML
    ImageView wrongNicknameImage;

    @FXML
    ImageView wrongIpImage;

    @FXML
    protected void onGoBackButtonClick() {
        //back to the lobby
        StageController.changeScene("lobby.fxml","Lobby");
    }

    @FXML
    protected void onContinueButtonClick() {
        StageController.changeScene("game_info_scene.fxml","Set number of players");
    }

    @FXML
    public void setContinueButtonVisible() {
        if(nicknameField.getText().length()>0 ) //TODO && nickname valid && ip valid
            continueLoginBtn.setVisible(true);
    }

    @FXML
    public void onNicknameInserted()  {
        if(true) {   //TODO ckeck if nickname is valid
            wrongNicknameImage.setVisible(false);   //nickname is correct
        } else wrongNicknameImage.setVisible(true);
    }

    @FXML
    public void onIpInserted()  {
        if(true) {   //TODO ckeck if ip is valid
            wrongIpImage.setVisible(false);   //ip is correct
        } else wrongIpImage.setVisible(true);
    }

    public String getNickname() {
        return nicknameField.getText();
    }

    public String getIP() {
        return ipField.getText();
    }
}