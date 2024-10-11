package entityView;

import entity.Client;

import java.util.Date;

public class ClientComplet extends Client {
    private String id;
    private String nom;
    private Date dateNaissance;
    private int idGenre;
    private String genre;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public Date getDateNaissance() {
        return dateNaissance;
    }

    @Override
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public int getIdGenre() {
        return idGenre;
    }

    @Override
    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ClientComplet() {
        super();
    }

    public ClientComplet(String id) {
        super(id);
    }

    public ClientComplet(String id, String nom, Date dateNaissance, int idGenre, String genre) {
        super(id, nom, dateNaissance, idGenre);
        this.setNom(nom);
        this.genre = genre;
    }

    public ClientComplet(String nom, Date dateNaissance, int idGenre, String genre) {
        super(nom, dateNaissance, idGenre);
        this.genre = genre;
    }
}
