package entities;

public class Article {
    private int id;
    private String titre_art,contenue,img_art;
    private int likes,dislikes,nbr_like;

    private Sujet sujet_id;
    public Article(){

    }

    public Article(int id, String titre_art, String contenue, String img_art, int likes, int dislikes, int nbr_like, Sujet sujet_id) {
        this.id = id;
        this.titre_art = titre_art;
        this.contenue = contenue;
        this.img_art = img_art;
        this.likes = likes;
        this.dislikes = dislikes;
        this.nbr_like = nbr_like;
        this.sujet_id = sujet_id;
    }

    public Article(String titre_art, String contenue, String img_art, int likes, int dislikes, int nbr_like, Sujet sujet_id) {
        this.titre_art = titre_art;
        this.contenue = contenue;
        this.img_art = img_art;
        this.likes = likes;
        this.dislikes = dislikes;
        this.nbr_like = nbr_like;
        this.sujet_id = sujet_id;
    }

    public Article(String titre_art, String contenue, String img_art, Sujet sujet_id) {
        this.titre_art = titre_art;
        this.contenue = contenue;
        this.img_art = img_art;
        this.sujet_id = sujet_id;
    }

    public Article(String titre_art, String contenue, String img_art) {
        this.titre_art = titre_art;
        this.contenue = contenue;
        this.img_art = img_art;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_art() {
        return titre_art;
    }

    public void setTitre_art(String titre_art) {
        this.titre_art = titre_art;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public String getImg_art() {
        return img_art;
    }

    public void setImg_art(String img_art) {
        this.img_art = img_art;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getNbr_like() {
        return nbr_like;
    }

    public void setNbr_like(int nbr_like) {
        this.nbr_like = nbr_like;
    }

    public Sujet getSujet_id() {
        return sujet_id;
    }

    public void setSujet_id(Sujet sujet_id) {
        this.sujet_id = sujet_id;
    }
}
