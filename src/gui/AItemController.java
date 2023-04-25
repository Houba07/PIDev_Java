package gui;

import entities.Article;
import entities.Forum;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.ArticleService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AItemController implements Initializable {

    @FXML
    private Label sujetArt;
    @FXML
    private Label nbrLikeLabel;
    private boolean isLiked;
    private Article article;

    @FXML
    private Label contenuArt;

    @FXML
    private ImageView imageA;

    @FXML
    private Label titreArt;
    private ArticleService as = new ArticleService();
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
            this.nbrLikeLabel.setText(String.valueOf("J'aime :  " + this.article.getNbr_like()));



        } else {

            titreArt.setText("");
            contenuArt.setText("");
            sujetArt.setText("");
            nbrLikeLabel.setText("");
        }
    }

    public void love(){
        try {
            if (!isLiked) {
                isLiked = true;
                as.Like(article);
                nbrLikeLabel.setText((article.getNbr_like() + 1) + " personnes aiment ça");
            } else {
                isLiked = false;
                as.DisLike(article);
                nbrLikeLabel.setText((article.getNbr_like()) + " personnes aiment ça");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur" + ex.getMessage());
        }
    }
}
