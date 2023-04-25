package gui;

import entities.Article;
import entities.Forum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FItemController implements Initializable {

    @FXML
    private Label categorieForum;
    private Forum forum;
    private Article article;

    @FXML
    private Label descForum;

    @FXML
    private ImageView imageF;

    @FXML
    private Label titreForum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateUI();
    }

    public void setForum(Forum forum) {
        this.forum = forum;
        updateUI();
    }

    private void updateUI() {
        if (forum != null) {
            String img = forum.getImg_forum();
            String ch = "../images/";
            String imgF = ch + img;
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgF)));
            this.imageF.setImage(image);
            Label var10000 = this.titreForum;
            String var10001 = this.forum.getTitre_forum();
            var10000.setText("Titre :  " + this.forum.getTitre_forum());
            this.descForum.setText("Description :  " +this.forum.getDescription());
            this.categorieForum.setText(String.valueOf("Catégorie :  " + this.forum.getCategorie_id()));


        } else {

            titreForum.setText("");
            descForum.setText("");
            categorieForum.setText("");
        }
    }

    public void commenter(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/gui/AjouterCom.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }


}
