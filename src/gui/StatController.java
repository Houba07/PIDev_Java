package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.MyDB;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatController implements Initializable {

    Connection connexion ;
    Statement stm;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private PieChart pieChart;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            afficherPie();
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public StatController() {
        connexion = MyDB.getInstance().getCnx();
    }
    public void afficherPie() throws SQLException{
        int infCatsCount=0 ;
        int gynecologieCatsCount=0 ;
        int cardiologieCatsCount=0 ;
        int nutritionisteCatsCount=0 ;
        int psychiatrieCatsCount=0 ;
        String req = "SELECT COUNT(*) AS count FROM categorie WHERE nom_cat='gynécologie' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            infCatsCount = rst.getInt(1);
        }
        PieChart.Data qtr1= new PieChart.Data("Catégorie cardiologie", +infCatsCount) ;
        String req1 = "SELECT COUNT(*) AS count FROM categorie WHERE nom_cat='cardiologie' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst1 = stm.executeQuery(req1);

        while (rst1.next()) {
            nutritionisteCatsCount = rst1.getInt(1);
        }
        PieChart.Data qtr2= new PieChart.Data("Catégorie nutritioniste", +nutritionisteCatsCount) ;
        String req2 = "SELECT COUNT(*) AS count FROM categorie WHERE nom_cat='nutritioniste' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst2 = stm.executeQuery(req2);

        while (rst2.next()) {
            psychiatrieCatsCount = rst2.getInt(1);
        }
        PieChart.Data qtr3= new PieChart.Data("Catégorie psychiatrie", +psychiatrieCatsCount) ;
        String req3 = "SELECT COUNT(*) AS count FROM categorie WHERE nom_cat='psychiatrie' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst3 = stm.executeQuery(req3);

        while (rst3.next()) {
            gynecologieCatsCount = rst3.getInt(1);
        }
        PieChart.Data qtr4= new PieChart.Data("Catégorie gynécologie ", +gynecologieCatsCount) ;

        String req4 = "SELECT COUNT(*) AS count FROM categorie WHERE nom_cat='gynécologie' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst4 = stm.executeQuery(req4);



        pieChart.getData().addAll(qtr1,qtr2,qtr3,qtr4) ;

    }
}
