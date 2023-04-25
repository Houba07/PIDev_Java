package services;


import entities.Commentaire;
import entities.Forum;
import utils.MyDB;
import java.sql.Connection;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public  class CommentaireService implements ICommentaire<Commentaire> {

    Connection cnx;

    public CommentaireService() {cnx = MyDB.getInstance().getCnx() ;}
    public void ajouterCom (Commentaire c) throws SQLException {
        try {
            java.util.Date javaDate = new java.util.Date();
        java.sql.Date date_com = new java.sql.Date(javaDate.getTime());
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  commentaire (cmt) VALUES" +
                "(?)");

        statement.setString(1, c.getCmt());

        statement.executeUpdate();
        System.out.println("Commentaire  ajoutée");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }



    }

    @Override
    public void modifierCom(Commentaire c) throws SQLException {
        try {

            String req = "update commentaire set cmt=? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, c.getCmt());
            ps.setInt(2, (int) c.getId());
            ps.executeUpdate();
            System.out.println("Commentaire modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerCom (int id) throws SQLException{
        try
        {
            Statement st = cnx.createStatement();
            String req = "DELETE FROM commentaire WHERE id = "+id+"";
            st.executeUpdate(req);
            System.out.println("Commentaire supprimé avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    public List<Commentaire> recuperer() throws SQLException, ParseException {
        List<Commentaire> com= new ArrayList<>();
        try {
            String sql = "select * from commentaire";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);

            while (rs.next()) {
                Commentaire cmt = new Commentaire(rs.getInt("id"),rs.getString("cmt"),rs.getDate("date_com"));
                com.add(cmt);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return com;
}
}
