package entities;

import java.sql.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commentaire)) return false;
        Commentaire that = (Commentaire) o;
        return id == that.id && Objects.equals(cmt, that.cmt) && Objects.equals(forum, that.forum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cmt, forum);
    }
}
