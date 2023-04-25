package services;

import entities.Categorie;
import utils.MyDB;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements ICategorie <Categorie>{

    Connection cnx;

    public CategorieService() {
        cnx = MyDB.getInstance().getCnx() ;
    }

    public void ajouterC(Categorie cat) throws SQLException, ParseException {
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  categorie (nom_cat) VALUES" +
                "(?)");

        statement.setString(1, cat.getNom_cat());
        statement.executeUpdate();

    }

    @Override
    public List<Categorie> recuperer() throws SQLException, ParseException {
        List<Categorie> cat = new ArrayList<>();
        String R = "select * from categorie";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(R);
        while (rs.next()) {
            Categorie stt = new Categorie(rs.getInt("id"),rs.getString("nom_cat"));
            cat.add(stt);

        }
        return cat;
    }

    @Override
    public void modifierC(Categorie cat) throws SQLException {
        String query = "UPDATE  categorie set nom_cat=? Where id ='" + cat.getId() + "'";

        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setString(1, cat.getNom_cat());


            ste.executeUpdate();
            System.out.println("La catégorie a ete modifiée avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerC(int id) throws SQLException {
        String sql = "DELETE FROM `categorie` WHERE `categorie`.`id`=?";

            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("catégorie supprimée");


    }

    public Categorie getCatWithId(int id) throws SQLException {
        List<String> cats = new ArrayList<>();
        String R = "select * from categorie where id='" + id + "'";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(R);
        Categorie fm = null;
        while (rs.next()) {
            fm = new Categorie();

            fm.setId(rs.getInt("id"));
            fm.setNom_cat(rs.getString("nom_cat"));
        }
        return fm;

    }





}
