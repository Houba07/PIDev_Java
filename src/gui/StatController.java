package gui;

import entities.Sujet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.SujetService;
import utils.MyDB;

import java.io.IOException;
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
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    SujetService ss = new SujetService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        pieChartData.clear();

        pieChartData.clear();

        ResultSet rs = ss.nbr();
        try {
            while (rs.next()) {
                String TYPE = rs.getString("nom_sujet");
                int count = rs.getInt("totallike");
//                System.out.println(category + ": " + count);
                PieChart.Data data = new PieChart.Data(TYPE, count);
                pieChartData.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pieChart.setData(pieChartData);


    }
    public void Home(ActionEvent event) throws IOException {
        Parent AjouterParent = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
        Scene AjouterScene = new Scene(AjouterParent);

        //this line gets the stage by force
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(AjouterScene);
        window.show();
    }

}
