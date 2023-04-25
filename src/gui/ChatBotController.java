package gui;

import entities.ChatBot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatBotController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField inputTextField;

    @FXML
    private TextArea outputLabel;
    private ChatBot chatbot;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chatbot = new ChatBot();
    }

    @FXML
    private void processInput(ActionEvent event) {
        String input = inputTextField.getText();
        String output = chatbot.processInput(input);
        outputLabel.setText(output);
        inputTextField.clear();
    }

    public void Accueil(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }
}
