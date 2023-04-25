package services;

import entities.Sujet;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ISujet <S>{
    public abstract void ajouterS(S s) throws SQLException, ParseException;
    List<S> recuperer() throws SQLException, ParseException;


    void supprimerS(int id) throws SQLException;

    void modifierS(S s) throws SQLException;
}
