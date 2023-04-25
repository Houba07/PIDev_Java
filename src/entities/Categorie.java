package entities;

import java.util.Collection;

public class Categorie {
    private int id;
    private String nom_cat;

    public Categorie(int id) {
        this.id = id;
    }

    public Categorie() {
    }
    public Categorie(int id, String nom_cat) {
        this.id = id;
        this.nom_cat = nom_cat;
    }


    public Categorie(String nom_cat) {
        this.nom_cat = nom_cat;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cat() {
        return nom_cat;
    }

    public void setNom_cat(String nom_cat) {
        this.nom_cat = nom_cat;
    }

    @Override
    public String toString() {
        return  nom_cat;
    }
}
