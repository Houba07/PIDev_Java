package entities;

import java.sql.Date;

public class Forum {
    private int id;
    private String titre_forum,nom_user,img_forum,visibilite;
    private String description;
    private Date date_forum;
    private Categorie categorie_id;


    public Forum() {
    }

    public Forum(int id,Categorie categorie_id, String titre_forum, String nom_user, String img_forum, String visibilite, String description, Date date_forum) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.titre_forum = titre_forum;
        this.nom_user = nom_user;
        this.img_forum = img_forum;
        this.visibilite = visibilite;
        this.description = description;
        this.date_forum = date_forum;

    }

    public Forum(Categorie categorie_id, String titre_forum, String nom_user, String img_forum, String visibilite, String description, Date date_forum) {
        this.categorie_id = categorie_id;
        this.titre_forum = titre_forum;
        this.nom_user = nom_user;
        this.img_forum = img_forum;
        this.visibilite = visibilite;
        this.description = description;
        this.date_forum = date_forum;

    }

    public Forum(int id, Categorie categorie_id, String titre_forum, String img_forum, String visibilite, String description) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.titre_forum = titre_forum;
        this.img_forum = img_forum;
        this.visibilite = visibilite;
        this.description = description;

    }

    public Forum(String titre_forum) {
        this.titre_forum = titre_forum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_forum() {
        return titre_forum;
    }

    public void setTitre_forum(String titre_forum) {
        this.titre_forum = titre_forum;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getImg_forum() {
        return img_forum;
    }

    public void setImg_forum(String img_forum) {
        this.img_forum = img_forum;
    }

    public String getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(String visibilite) {
        this.visibilite = visibilite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_forum() {
        return date_forum;
    }

    public void setDate_forum(Date date_forum) {
        this.date_forum = date_forum;
    }

    public Categorie getCategorie_id() { return categorie_id; }

    public void setCategorie_id(Categorie categorie_id) { this.categorie_id = categorie_id; }
}
