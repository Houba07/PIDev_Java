package gui;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ArticleService;
import services.CategorieService;
import services.ForumService;
import utils.MyDB;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FrontController implements Initializable {

    @FXML
    private FlowPane flowF;

    @FXML
    private FlowPane flowA;
    @FXML
    private TextField searchField;

    @FXML
    private TextField searchFieldArt;

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



        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateView(newValue);
        });
        searchFieldArt.textProperty().addListener((observable, oldValue, newValue) -> {
            updateViewArt(newValue);
        });

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

    private void updateView(String searchQuery) {
        if (flowF != null) {
            try {
                List<Forum> listF = null;
                listF = fs.recuperer();
                flowF.getChildren().clear(); // clear the current products

                // filter the list of products based on the search query
                List<Forum> filteredF = listF.stream()
                        .filter(p -> p.getTitre_forum().toLowerCase().contains(searchQuery.toLowerCase()))
                        .collect(Collectors.toList());

                for (Forum forum1 : filteredF) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/FItem.fxml"));
                    Parent parent = fxmlLoader.load();
                    FItemController fitemController = fxmlLoader.getController();
                    fitemController.setForum(forum1);
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
            System.out.println("productsFlowPane is null");
        }
    }

    @FXML
    private void updateViewByTitre() {
        if (flowF != null) {
            try {
                List<Forum> listProd = fs.getByTitre();
                flowF.getChildren().clear();

                for (Forum forum1 : listProd) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/FItem.fxml"));
                    Parent parent = fxmlLoader.load();
                    FItemController fitemController = fxmlLoader.getController();
                    fitemController.setForum(forum1);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    flowF.getChildren().add(node);
                }
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("rvFlowPane is null");
        }
    }

    private void updateViewArt(String searchQuery) {
        if (flowA != null) {
            try {
                List<Article> listA = null;
                listA = as.recuperer();
                flowA.getChildren().clear(); // clear the current products

                // filter the list of products based on the search query
                List<Article> filteredA = listA.stream()
                        .filter(p -> p.getTitre_art().toLowerCase().contains(searchQuery.toLowerCase()))
                        .collect(Collectors.toList());

                for (Article article : filteredA) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/AItem.fxml"));
                    Parent parent = fxmlLoader.load();
                    AItemController aitemController = fxmlLoader.getController();
                    aitemController.setArticle(article);
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
            System.out.println("productsFlowPane is null");
        }
    }

    @FXML
    private void updateViewByNbrLike() {
        if (flowA != null) {
            try {
                List<Article > like = as.getByNbrLike();
                flowA.getChildren().clear();

                for (Article article : like) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/AItem.fxml"));
                    Parent parent = fxmlLoader.load();
                    AItemController aitemController = fxmlLoader.getController();
                    aitemController.setArticle(article);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    flowA.getChildren().add(node);
                }
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("productsFlowPane is null");
        }
    }

    @FXML
    private void updateViewByTitreArt() {
        if (flowA != null) {
            try {
                List<Article > articles = as.getByTitreArt();
                flowA.getChildren().clear();

                for (Article article : articles) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/AItem.fxml"));
                    Parent parent = fxmlLoader.load();
                    AItemController aitemController = fxmlLoader.getController();
                    aitemController.setArticle(article);
                    Region region = (Region) parent;
                    Node node = region.getChildrenUnmodifiable().get(0);
                    flowA.getChildren().add(node);
                }
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("productsFlowPane is null");
        }
    }

    @FXML
    private void imprimer(ActionEvent event)throws SQLException, DocumentException, IOException {
        java.sql.Connection cnx = MyDB.getInstance().getCnx();
        String requete1 = "SELECT id ,categorie_id,titre_forum,description,img_forum,visibilite FROM  forum";
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\ryhab\\OneDrive\\Bureau\\Forum.pdf"));
            document.open();

//       Image img = Image.getInstance("C:\\Users\\21655\\Downloads\\badelBd\\projet de amine 2\\projet de amine 2\\projet de amine\\projet de amine\\PidevJava\\src\\gui\\dronify.png");
//       img.scaleAbsoluteHeight(60);
//       img.scaleAbsoluteWidth(100);
//       img.setAlignment(Image.ALIGN_LEFT);
//       document.open();
//       document.add(img);

            //document.add(new Paragraph("Liste des paiements"));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("Liste des Forums ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));


            PdfPTable tabpdf = new PdfPTable(6);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("ID Forum", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Categorie", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Titre", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Image", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);


            cell = new PdfPCell(new Phrase("Visibilite", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);





            PreparedStatement pst = cnx.prepareStatement(requete1);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("id"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("categorie_id"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("titre_forum"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("description"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("img_forum"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("visibilite"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);



            }
            document.add(tabpdf);
            JOptionPane.showMessageDialog(null, "Success !");
            document.close();
            Desktop.getDesktop().open(new File("C:\\Users\\ryhab\\OneDrive\\Bureau\\Forum.pdf"));
//          notif("Succes","Votre document a été enregistré au format PDF !");
        }

        catch (SQLException | DocumentException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }


}
