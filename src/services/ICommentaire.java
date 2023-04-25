package services;

import entities.Commentaire;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ICommentaire <Com>{
    public void ajouterCom (Com cmt) throws SQLException;

    void modifierCom(Com cmt) throws SQLException;

    List<Commentaire> recuperer() throws SQLException, ParseException;
}
