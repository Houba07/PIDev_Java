package services;


import entities.Forum;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IForum<F>{
    public abstract void ajouterF(Forum f) throws SQLException, ParseException;

    public void modifierF(Forum f) throws SQLException;

    public boolean supprimerF(int id);

    List<Forum> recuperer() throws SQLException, ParseException;
}
