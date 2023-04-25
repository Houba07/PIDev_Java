package services;

import entities.Categorie;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ICategorie <C>{
    public abstract void ajouterC(C c) throws SQLException, ParseException;

    List<C> recuperer() throws SQLException, ParseException;

    public abstract void modifierC(C c) throws SQLException, ParseException;

    void supprimerC(int id) throws SQLException;
}
