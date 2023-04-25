package services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import entities.Article;

public interface IArticle<A> {

    public abstract void ajouterA(Article a) throws SQLException, ParseException;


    void modifierA(Article a) throws SQLException;

    public boolean supprimerA(int id);

    public List<Article> recuperer() throws SQLException, ParseException;
}
