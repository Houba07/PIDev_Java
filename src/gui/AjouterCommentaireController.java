package gui;

import entities.Commentaire;
import entities.Sujet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CommentaireService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AjouterCommentaireController implements Initializable {

    @FXML
    private TextField desc;


    CommentaireService cs = new CommentaireService();

    @FXML
    void AjouterCom(ActionEvent event) {
        Commentaire com = new Commentaire();
        com.setCmt(desc.getText());
        try {
            if (com.getCmt().length()==0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de publier votre commentaire ! ");
                alert.setHeaderText("Veuillez remplir tous les champs de votre commentaire !");
                alert.showAndWait();
                return;
            }
            cs.ajouterCom(com);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    private void reset() {
        desc.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void AfficherCom(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/gui/Back.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }
}
