package gui;
import entities.*;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.*;

import javax.swing.*;
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
import java.util.stream.Collectors;


public class BackController implements Initializable {



    @FXML
    private TextField cat;

    @FXML
    private TableColumn<Categorie, String> catCol;

    @FXML
    private TextField contenu;

    @FXML
    private TableColumn<Article, String> contenuCol;

    @FXML
    private TableColumn<Forum, String> descCol;


    @FXML
    private TextField idA;

    @FXML
    private TableColumn<Article, Integer> idACol;

    @FXML
    private TextField idC;

    @FXML
    private TableColumn<Categorie, Integer> idCCol;
    @FXML
    private TableColumn<Forum, Integer> idFCol;

    @FXML
    private TextField idS;

    @FXML
    private TableColumn<Sujet, Integer> idSCol;

    @FXML
    private TextField image;
    @FXML
    private ChoiceBox<String> visible;
    @FXML
    private ImageView imageAff;
    @FXML
    private TextField imageF;
    @FXML
    private TextField idF;
    @FXML
    private TextField titreF;
    @FXML
    private TextField desc;


    @FXML
    private TextField suj;

    @FXML
    private TableColumn<Sujet, String> sujCol;

    @FXML
    private ComboBox<Sujet> sujet = new ComboBox<>();

    @FXML
    private TableColumn<Article, String> sujetCol;
    @FXML
    private TableColumn<Forum, String> categoCol;
    @FXML
    private TableColumn<Forum, String> visibleCol;

    @FXML
    private TableView<Forum> tabF;
    @FXML
    private TableView<Article> tabA;

    @FXML
    private TableView<Categorie> tabC;

    @FXML
    private TableView<Sujet> tabS;
    @FXML
    private TableView<Commentaire> tabCom;

    @FXML
    private TextField titre;
    @FXML
    private TextField cmt;
    @FXML
    private TextField idCom;
    @FXML
    private TableColumn<Commentaire, String> idComCol;
    @FXML
    private TableColumn<Commentaire, String> cmtCol;

    @FXML
    private TableColumn<Article, String> titreCol;
    @FXML
    private TableColumn<Forum, String> titreFCol;
    private byte[] fichier;
    SujetService ss = new SujetService();
    ArticleService as = new ArticleService();
    CategorieService cs = new CategorieService();
    ForumService fs = new ForumService();
    CommentaireService coms = new CommentaireService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {

                ObservableList<Categorie> observableUserList = FXCollections.observableList(cs.recuperer());
                tabC.setItems(observableUserList);
                tabC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                idCCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                catCol.setCellValueFactory(new PropertyValueFactory<>("nom_cat"));

                tabC.setOnMouseClicked(event -> {
                    Categorie comSelectionne = tabC.getSelectionModel().getSelectedItem();
                    if (comSelectionne != null) {

                        idC.setText(Integer.toString(comSelectionne.getId()));
                        cat.setText(comSelectionne.getNom_cat());

                    }
                });
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        try {

            ObservableList<Sujet> observableUserList = FXCollections.observableList(ss.recuperer());
            tabS.setItems(observableUserList);
            tabS.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            idSCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            sujCol.setCellValueFactory(new PropertyValueFactory<>("nom_sujet"));


            tabS.setOnMouseClicked(event -> {
                Sujet comSelectionne = tabS.getSelectionModel().getSelectedItem();
                if (comSelectionne != null) {

                    idS.setText(Integer.toString(comSelectionne.getId()));
                    suj.setText(comSelectionne.getNom_sujet().toString());

                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {

            ObservableList<Article> observableUserList = FXCollections.observableList(as.recuperer());
            tabA.setItems(observableUserList);
            tabA.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            idACol.setCellValueFactory(new PropertyValueFactory<>("id"));
            titreCol.setCellValueFactory(new PropertyValueFactory<>("titre_art"));
            contenuCol.setCellValueFactory(new PropertyValueFactory<>("contenue"));
            sujetCol.setCellValueFactory(new PropertyValueFactory<>("sujet_id"));




            tabA.setOnMouseClicked(event -> {
                Article comSelectionne = tabA.getSelectionModel().getSelectedItem();
                if (comSelectionne != null) {
                    idA.setText(Integer.toString(comSelectionne.getId()));
                    titre.setText(comSelectionne.getTitre_art());
                    contenu.setText(comSelectionne.getContenue());
                    image.setText(String.valueOf(comSelectionne.getImg_art()));


                }
            });
        } catch (SQLException | ParseException ex) {
            System.out.println(ex);
        }

        try {
            //ObservableList<String> typeOptions = FXCollections.observableArrayList("Anonyme", "Publique");
            //visible.setItems(typeOptions);
            ObservableList<Forum> observableUserList = FXCollections.observableList(fs.recuperer());
            tabF.setItems(observableUserList);
            tabF.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            idFCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            titreFCol.setCellValueFactory(new PropertyValueFactory<>("titre_forum"));
            descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            categoCol.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
            visibleCol.setCellValueFactory(new PropertyValueFactory<>("visibilite"));

            tabF.setOnMouseClicked(event -> {
                Forum comSelectionne = tabF.getSelectionModel().getSelectedItem();
                if (comSelectionne != null) {
                    idF.setText(Integer.toString(comSelectionne.getId()));
                    titreF.setText(comSelectionne.getTitre_forum());
                    desc.setText(comSelectionne.getDescription());
                    imageF.setText(String.valueOf(comSelectionne.getImg_forum()));




                }
            });
        } catch (SQLException | ParseException ex) {
            System.out.println(ex);
        }
        try {

            ObservableList<Commentaire> observableUserList = FXCollections.observableList(coms.recuperer());
            tabCom.setItems(observableUserList);
            tabCom.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            idComCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            cmtCol.setCellValueFactory(new PropertyValueFactory<>("cmt"));


            tabCom.setOnMouseClicked(event -> {
                Commentaire comSelectionne = tabCom.getSelectionModel().getSelectedItem();
                if (comSelectionne != null) {

                    idCom.setText(Integer.toString(comSelectionne.getId()));
                    cmt.setText(comSelectionne.getCmt());

                }
            });
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }



    //article

    @FXML
    public void supprimerA(ActionEvent actionEvent) throws SQLException, ParseException {
        int ida=Integer.parseInt(idA.getText());


        as.supprimerA(ida);
        tabA.refresh();
        ObservableList<Article> observableUserList = FXCollections.observableList(as.recuperer());
        tabA.setItems(observableUserList);
        tabA.getSelectionModel().clearSelection();
        idA.clear();
        titre.clear();
        contenu.clear();
        image.clear();
    }
    private void load() throws SQLException, ParseException {
        List<Sujet> sjt=ss.recuperer();
        for(Sujet i : sjt) {
            sujet.getItems().add(i);
        }
    }

    @FXML
    private void parcourir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            image.setText(file.getAbsolutePath());
            try {
                Path path = Paths.get(file.getAbsolutePath());
                fichier = Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void parcourirF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            image.setText(file.getAbsolutePath());
            try {
                Path path = Paths.get(file.getAbsolutePath());
                fichier = Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @FXML
    private void ajouterA(ActionEvent event) throws SQLException, ParseException {
        Article art = new Article();
        try {

            if (titre.getText().length()==0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur !");
                alert.setHeaderText("Ce champs est requis !");
                alert.showAndWait();
                return;
            }

            if (contenu.getText().length()==0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur !");
                alert.setHeaderText("Veuillez remplir ce champs !");
                alert.showAndWait();
                return;
            }


            // Appel du service pour ajouter l'article
            ArticleService as = new ArticleService();
            as.ajouterA(art);

            // Réinitialisation des champs
            resetA();
            JOptionPane.showMessageDialog(null, "Article ajouté avec succes ! " );

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierA(ActionEvent actionEvent) throws SQLException, ParseException {
        Article a = new Article();
        a.setId(Integer.parseInt(idA.getText()));

        a.setTitre_art(titre.getText());

        a.setContenue(contenu.getText());

        a.setImg_art(image.getText());
        as.modifierA(a);
        tabA.refresh();
        ObservableList<Article> observableUserList = FXCollections.observableList(as.recuperer());
        tabA.setItems(observableUserList);
        tabA.getSelectionModel().clearSelection();
        idA.clear();
        titre.clear();
        contenu.clear();
        image.clear();
    }
    private void resetA() {
        // Réinitialisation des champs
        titre.setText("");
        contenu.setText("");
        image.setText("");


    }

    //Categorie
    @FXML
    void AjouterC(ActionEvent event) {
        Categorie cate = new Categorie();
        cate.setNom_cat(cat.getText());
        try {
            if (cate.getNom_cat().length()==0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de publier votre categorie ! ");
                alert.setHeaderText("Veuillez remplir tous les champs de votre categorie !");
                alert.showAndWait();
                return;
            }
            cs.ajouterC(cate);
            resetC();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    private void resetC() {
        cat.setText("");
    }

    @FXML
    public void supprimerC(ActionEvent actionEvent) throws SQLException, ParseException {
        int idcat=Integer.parseInt(idC.getText());


        cs.supprimerC(idcat);
        tabC.refresh();
        ObservableList<Categorie> observableUserList = FXCollections.observableList(cs.recuperer());
        tabC.setItems(observableUserList);
        tabC.getSelectionModel().clearSelection();
        idC.clear();
        cat.clear();
    }

    public void modifierC(ActionEvent actionEvent) throws SQLException, ParseException {
        Categorie c = new Categorie();

        c.setId(Integer.parseInt(idC.getText()));
        c.setNom_cat(cat.getText());
        cs.modifierC(c);
        tabC.refresh();
        ObservableList<Categorie> observableUserList = FXCollections.observableList(cs.recuperer());
        tabC.setItems(observableUserList);
        tabC.getSelectionModel().clearSelection();
        idC.clear();
        cat.clear();
    }

    //Sujet
    @FXML
    void AjouterS(ActionEvent event) {
        Sujet sjt = new Sujet();
        sjt.setNom_sujet(suj.getText());
        try {

            ss.ajouterS(sjt);
            resetS();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private void resetS() {
        suj.setText("");
    }

    public void modifierS(ActionEvent actionEvent) throws SQLException, ParseException {
        Sujet s = new Sujet();

        s.setId(Integer.parseInt(idS.getText()));
        s.setNom_sujet(suj.getText());
        ss.modifierS(s);
        tabS.refresh();
        ObservableList<Sujet> observableUserList = FXCollections.observableList(ss.recuperer());
        tabS.setItems(observableUserList);
        tabS.getSelectionModel().clearSelection();
        idS.clear();
        suj.clear();
    }

    @FXML
    public void supprimerS(ActionEvent actionEvent) throws SQLException, ParseException {
        int ids=Integer.parseInt(idS.getText());


        ss.supprimerS(ids);
        tabS.refresh();
        ObservableList<Sujet> observableUserList = FXCollections.observableList(ss.recuperer());
        tabS.setItems(observableUserList);
        tabS.getSelectionModel().clearSelection();
        idS.clear();
        suj.clear();
    }

    public void AfficherFront(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/gui/Front.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

    @FXML
    public void supprimerF(ActionEvent actionEvent) throws SQLException, ParseException {
        int idf=Integer.parseInt(idF.getText());


        fs.supprimerF(idf);
        tabF.refresh();
        ObservableList<Forum> observableUserList = FXCollections.observableList(fs.recuperer());
        tabF.setItems(observableUserList);
        tabF.getSelectionModel().clearSelection();
        idF.clear();
        titre.clear();
        desc.clear();
        image.clear();
    }

    @FXML
    public void supprimerCom(ActionEvent actionEvent) throws SQLException, ParseException {
        int idc=Integer.parseInt(idCom.getText());


        coms.supprimerCom(idc);
        tabCom.refresh();
        ObservableList<Commentaire> observableUserList = FXCollections.observableList(coms.recuperer());
        tabCom.setItems(observableUserList);
        tabCom.getSelectionModel().clearSelection();
        idCom.clear();
        cmt.clear();
    }

    public void Stat(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/gui/Stat.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }




}

