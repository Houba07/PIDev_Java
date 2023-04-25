package entities;

public class Sujet {
    private int id;
    private String nom_sujet;


    public Sujet(int id) {
        this.id = id;
    }
    public Sujet() {
    }
    public Sujet(int id, String nom_sujet) {
        this.id = id;
        this.nom_sujet = nom_sujet;
    }


    public Sujet(String nom_sujet) {
        this.nom_sujet = nom_sujet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_sujet() {
        return nom_sujet;
    }

    public void setNom_sujet(String nom_sujet) {
        this.nom_sujet = nom_sujet;
    }

    @Override
    public String toString() {
        return
                 nom_sujet;
    }
}
