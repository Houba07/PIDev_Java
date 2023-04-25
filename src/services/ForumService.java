package services;



import entities.Categorie;
import entities.Forum;
import utils.MyDB;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ForumService implements IForum <Forum>{

    Connection cnx;

    public ForumService() {
        cnx = MyDB.getInstance().getCnx() ;
    }

    public void ajouterF(Forum f) throws SQLException, ParseException {
        try{
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  forum (categorie_id,titre_forum,description,img_forum,visibilite) VALUES" +
                "(?, ?, ?,?,?)");

        statement.setInt(1, f.getCategorie_id().getId());
        statement.setString(2, f.getTitre_forum());
        statement.setString(3, f.getDescription());
        statement.setString(4, f.getImg_forum());
        statement.setString(5, f.getVisibilite());

        statement.executeUpdate();
        System.out.println("Forum  ajoutée");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }
    }




    @Override
    public void modifierF(Forum f) throws SQLException {
        String query = "UPDATE  forum set titre_forum=?,description=? ,img_forum=? ,visibilite=?   Where id ='" + f.getId() + "'";

        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setString(1, f.getTitre_forum());
            ste.setString(2, f.getDescription());
            ste.setString(3, f.getImg_forum());
            ste.setString(4, f.getVisibilite());


            ste.executeUpdate();
            System.out.println("L article a ete modifié avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public boolean supprimerF(int id) {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from forum where id = ? ");
            req.setInt(1, id);
            req.executeUpdate();
            ok = true;
            System.out.println("Forum supprimé !");
        } catch (SQLException ex) {
            System.out.println("Error in delete " + ex);
        }
        return ok;
    }

    @Override
    public List<Forum> recuperer() throws SQLException, ParseException{
        List<Forum> forums= new ArrayList<>();
        CategorieService fs = new CategorieService();

        String sql = "SELECT forum.id, forum.categorie_id , forum.titre_forum, forum.description,forum.img_forum,forum.visibilite, categorie.nom_cat AS nom_cat FROM forum LEFT JOIN categorie ON forum.categorie_id = categorie.id";
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);

        while (rs.next()) {
            Forum f = new Forum();
            f.setId(rs.getInt("id"));
            Categorie form=fs.getCatWithId(rs.getInt("categorie_id"));
            f.setCategorie_id(form);
            f.setTitre_forum(rs.getString("titre_forum"));
            f.setDescription(rs.getString("description"));
            f.setImg_forum(rs.getString("img_forum"));
            f.setVisibilite(rs.getString("visibilite"));
            forums.add(f);
        }

        return forums;
    }



}
