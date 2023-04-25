package test;

import entities.Article;
import entities.Categorie;
import entities.Sujet;
import services.ArticleService;
import services.CategorieService;
import services.SujetService;

import java.sql.SQLException;
import java.text.ParseException;

public class Test {
    public static void main(String[] args) throws RuntimeException, SQLException, ParseException {
        /*Categorie c = new Categorie("TESTETEST");
        CategorieService cs = new CategorieService();
        cs.ajouterC(c);*/

        Sujet s = new Sujet(7,"TESTETEST");
        /*SujetService cs = new SujetService();
        cs.ajouterS(s);*/

        /*Article a = new Article("test","testttttt","",0,0,0,s);
        ArticleService cs = new ArticleService();
        cs.ajouterA(a);*/
    }
}
