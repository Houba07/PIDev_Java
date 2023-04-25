package services;


import entities.Sujet;
import utils.MyDB;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SujetService implements ISujet <Sujet>{
    Connection cnx;

    public SujetService() {
        cnx = MyDB.getInstance().getCnx() ;
    }



    @Override
    public void ajouterS(Sujet sujet) throws SQLException, ParseException {
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  sujet (nom_sujet) VALUES" +
                "(?)");

        statement.setString(1, sujet.getNom_sujet());
        statement.executeUpdate();
    }

    @Override
    public List<Sujet> recuperer() throws SQLException, ParseException {
        List<Sujet> sjt = new ArrayList<>();
        String sql = "select * from sujet";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Sujet stt = new Sujet();

            stt.setId(rs.getInt("id"));
            stt.setNom_sujet(rs.getString("nom_sujet"));

            sjt.add(stt);

        }
        return sjt;
    }

    @Override
    public void supprimerS(int id) throws SQLException {
        String sql = "DELETE FROM `sujet` WHERE `sujet`.`id`=?";

        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ste.executeUpdate();
        System.out.println("catégorie supprimée");


    }

    @Override
    public void modifierS(Sujet sjt) throws SQLException {
        String query = "UPDATE  sujet set nom_sujet=? Where id ='" + sjt.getId() + "'";

        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setString(1, sjt.getNom_sujet());


            ste.executeUpdate();
            System.out.println("Le sujet a ete modifié avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }






    public Sujet  getSujetWithId(int id) throws SQLException {
        List<String> rvs = new ArrayList<>();
        String R = "select * from sujet where id='" + id + "'";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(R);
        Sujet fm = null;
        while (rs.next()) {
            fm = new Sujet();

            fm.setId(rs.getInt("id"));
            fm.setNom_sujet(rs.getString("nom_sujet"));
        }
        return fm;

    }

}
