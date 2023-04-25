package gui;

import entities.Article;
import entities.Forum;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AItemController implements Initializable {

    @FXML
    private Label sujetArt;
    private Article article;

    @FXML
    private Label contenuArt;

    @FXML
    private ImageView imageA;

    @FXML
    private Label titreArt;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateUI();
    }

    public void setArticle(Article article) {
        this.article = article;
        updateUI();

    }

    private void updateUI() {
        if (article != null) {


            String img = article.getImg_art();
            String ch = "../images/";
            String imgF = ch + img;
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgF)));
            this.imageA.setImage(image);



            Label var10000 = this.titreArt;
            //String var10001 = this.article.getTitre_art();
            var10000.setText("Titre :  " + this.article.getTitre_art());
            this.contenuArt.setText("Contenu :  " +this.article.getContenue());
            this.sujetArt.setText(String.valueOf("Sujet :  " + this.article.getSujet_id()));






        } else {

            titreArt.setText("");
            contenuArt.setText("");
            sujetArt.setText("");
        }
    }
}
