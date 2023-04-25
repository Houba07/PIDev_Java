package gui;

import entities.Article;
import entities.Categorie;
import entities.Forum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ArticleService;
import services.CategorieService;
import services.ForumService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class FrontController implements Initializable {

    @FXML
    private FlowPane flowF;

    @FXML
    private ScrollPane scrollF;

    @FXML
    private FlowPane flowA;

    @FXML
    private ScrollPane scrollA;

    private boolean isLiked;

    @FXML
    private ComboBox<Categorie> categorie = new ComboBox<>();

    @FXML
    private TextField desc;


    @FXML
    private TextField titre;
    @FXML
    private TextArea contenu;


    @FXML
    private TextField idF;

    @FXML
    private TextField imageF;

    @FXML
    private Button modifierF;

    @FXML
    private Button parcourir;

    @FXML
    private Button supprimerF;


    @FXML
    private TextField titreF;
    @FXML
    private ChoiceBox<String> visibilite;
    ArticleService as = new ArticleService();

    private String[] type = {"Anonyme","Publique"};
    public static Forum forum;
    Forum f = new Forum();
    ForumService fs = new ForumService();
    CategorieService cs = new CategorieService();
    Categorie c= new Categorie();
    private byte[] fichier;

    @FXML
    void AjouterF(ActionEvent event) throws SQLException, ParseException {
        if (titreF.getText().isEmpty()|| desc.getText().isEmpty()|| imageF.getText().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Ajout non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
            System.out.println("1");
        }



        else{
            c=categorie.getValue();
            f.setCategorie_id(c);
            f.setTitre_forum(titreF.getText());
            f.setImg_forum(imageF.getText());
            f.setVisibilite(visibilite.getValue());
            f.setDescription(desc.getText());
            fs.ajouterF(f);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Ajout effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }
        reset();



    }


    private void load() throws SQLException, ParseException {
        List<Categorie> cats=cs.recuperer();
        for(Categorie i : cats) {
            categorie.getItems().add(i);
        }
    }

    @FXML
    private void parcourir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            imageF.setText(file.getAbsolutePath());
            try {
                Path path = Paths.get(file.getAbsolutePath());
                fichier = Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> typeOptions = FXCollections.observableArrayList("Anonyme", "Publique");
        visibilite.setItems(typeOptions);

        try {
            load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        if (flowF != null) {

            try {
                List<Forum> listProd = null;
                listProd = fs.recuperer();
                for (Forum forum1 : listProd) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/FItem.fxml"));
                    Parent parent = fxmlLoader.load();
                    FItemController fItemController = fxmlLoader.getController();
                    fItemController.setForum(forum1);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    flowF.getChildren().add(node);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("ForumFlowPane is null");
        }

        if (flowA != null) {

            try {
                List<Article> listArt = null;
                listArt = as.recuperer();
                for (Article art : listArt) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/AItem.fxml"));
                    Parent parent = fxmlLoader.load();
                    AItemController fItemController = fxmlLoader.getController();
                    fItemController.setArticle(art);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    flowA.getChildren().add(node);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("ForumFlowPane is null");
        }


    }



    private void reset() {
        // Réinitialisation des champs
        visibilite.getSelectionModel().clearSelection();
        titreF.setText("");
        desc.setText("");
        imageF.setText("");

    }

    public void AfficherBack(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/gui/Back.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

    /*@FXML
    void Commenter(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterCom.fxml"));
            statutsVBox.getScene().setRoot(loader);

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }

    }*/


}
