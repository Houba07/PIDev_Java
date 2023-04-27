package services;

import entities.Article;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import entities.Sujet;
import utils.MyDB;


public class ArticleService implements IArticle <Article>{

    Connection cnx;

    public ArticleService() {
        cnx = MyDB.getInstance().getCnx() ;
    }

    public void ajouterA(Article a) throws SQLException, ParseException {

        try{
            PreparedStatement statement;
            statement = cnx.prepareStatement("INSERT INTO  article (sujet_idtitre_art,contenue,img_art) VALUES" +
                    "(?,?,?,?)");

            statement.setInt(1, a.getSujet_id().getId());
            statement.setString(2, a.getTitre_art());
            statement.setString(3, a.getContenue());
            statement.setString(4, a.getImg_art());



            statement.executeUpdate();
            System.out.println("Article  ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }


    @Override
    public void modifierA(Article a) throws SQLException {
        String query = "UPDATE  article set titre_art=?,contenue=? ,img_art=?   Where id ='" + a.getId() + "'";


        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setString(1, a.getTitre_art());
            ste.setString(2, a.getContenue());
            ste.setString(3, a.getImg_art());



            ste.executeUpdate();
            System.out.println("L article a ete modifié avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }
    @Override
    public boolean supprimerA(int id) {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from article where id = ? ");
            req.setInt(1, id);
            req.executeUpdate();
            ok = true;
            System.out.println("Article supprimé !");
        } catch (SQLException ex) {
            System.out.println("Error in delete " + ex);
        }
        return ok;
    }


    @Override
    public List<Article> recuperer() throws SQLException, ParseException{
        List<Article> arts= new ArrayList<>();
        SujetService ss = new SujetService();

            String sql = "SELECT article.id, article.sujet_id , article.titre_art, article.contenue,article.img_art, article.nbr_like,sujet.nom_sujet AS nom_sujet FROM article LEFT JOIN sujet ON article.sujet_id = sujet.id";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);

            while (rs.next()) {
                Article a = new Article();
                a.setId(rs.getInt("id"));
                Sujet sjt=ss.getSujetWithId(rs.getInt("sujet_id"));
                a.setSujet_id((sjt));
                a.setTitre_art(rs.getString("titre_art"));
                a.setContenue(rs.getString("contenue"));
                a.setImg_art(rs.getString("img_art"));
                a.setNbr_like(rs.getInt("nbr_like"));
                arts.add(a);
            }

        return arts;
    }

    public void Like(Article art) throws SQLException {
        PreparedStatement statement;
        statement = cnx.prepareStatement("UPDATE article SET nbr_like = nbr_like+1 WHERE id="+art.getId());
        statement.executeUpdate();
        System.out.println("Like réussi !");
    }
    public void DisLike(Article art) throws SQLException {

        PreparedStatement statement;
        statement = cnx.prepareStatement("UPDATE article SET nbr_like = nbr_like-1  WHERE id=" + art.getId());
        statement.executeUpdate();


        System.out.println("Like est supprimé !");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Article> getByNbrLike() throws SQLException, ParseException {
        List<Article> arts = recuperer();
        Collections.sort(arts, Comparator.comparing(Article::getNbr_like));
        return arts;
    }
    public List<Article> getByTitreArt() throws SQLException, ParseException {
        List<Article> articles = recuperer();
        Collections.sort(articles, Comparator.comparing(Article::getTitre_art));
        return articles;
    }


}
