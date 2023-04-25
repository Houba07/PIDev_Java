package entities;

import java.sql.Date;

public class Commentaire {
    private int id;
    private String cmt;
    private Forum forum;
    private Date date_com;

    public Commentaire(){

    }

    public Commentaire(int id, String cmt, Forum forum, Date date_com) {
        this.id = id;
        this.cmt = cmt;
        this.forum = forum;
        this.date_com = date_com;
    }

    public Commentaire(int id, String cmt) {
        this.id = id;
        this.cmt = cmt;
    }

    public Commentaire(String cmt, Forum forum, Date date_com) {
        this.cmt = cmt;
        this.forum = forum;
        this.date_com = date_com;
    }

    public Commentaire(int id, String cmt, Date date_com) {
        this.id = id;
        this.cmt = cmt;
        this.date_com = date_com;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Date getDate_com() {
        return date_com;
    }

    public void setDate_com(Date date_com) {
        this.date_com = date_com;
    }
}
