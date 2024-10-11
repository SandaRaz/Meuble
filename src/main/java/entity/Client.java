package entity;

import association.AchatClient;
import cnx.Connex;
import dao.GenericDao;
import entityView.ModeleAvecPrix;
import object.Panier;
import object.ProduitAchetee;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private String id;
    private String nom;
    private Date dateNaissance;
    private int idGenre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public Client() {
    }

    public Client(String id) {
        this.id = id;
    }

    public Client(String id, String nom, Date dateNaissance, int idGenre) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.idGenre = idGenre;
    }

    public Client(String nom, Date dateNaissance, int idGenre) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.idGenre = idGenre;
    }

    public void achat(Connection cnx, String idClient, String idModele, String quantiteString, String dateAchatString) throws Exception {
        boolean closed = false;
        boolean dejaAutoCommit = true;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            closed = true;
        }

        double quantite = 0;
        try{
            quantite = Double.parseDouble(quantiteString);
        }catch (NumberFormatException nfe){
            throw new Exception("Quantite non valide");
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateAchat = inputFormat.parse(dateAchatString);

        if(cnx.getAutoCommit()){
            cnx.setAutoCommit(false);
            dejaAutoCommit = false;
        }

        AchatClient achat = new AchatClient(idClient,idModele,quantite, dateAchat);
        ModeleAvecPrix modeleAvecPrix = (ModeleAvecPrix) GenericDao.FindById(cnx, new ModeleAvecPrix(idModele)).get(0);
        double prixRevient = modeleAvecPrix.getPrixDeRevient(cnx);
        double prixVente = modeleAvecPrix.getPrixDeVente(cnx);
        StockProduit stockProduit = new StockProduit(idModele,0, quantite, prixRevient, prixVente ,dateAchat);




        if(!dejaAutoCommit){
            cnx.setAutoCommit(true);
        }
        if(closed){
            cnx.close();
        }
    }

    public void Acheter(Connection cnx, Panier panier) throws Exception {
        boolean closed = false;
        boolean estAutoCommit = true;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            closed = true;
        }
        if(cnx.getAutoCommit()){
            cnx.setAutoCommit(false);
            estAutoCommit = false;
        }

        try{
            Client client = panier.getProprietaire();
            Date dateAchat = panier.getDateAchat();
            for(ProduitAchetee pa : panier.getListeProduits()){
                Modele modele = pa.getModele();

                AchatClient achat = new AchatClient(client.getId(),modele.getId(),pa.getQuantite(), dateAchat);

                double prixRevient = modele.getPrixDeRevient(cnx);
                double prixVente = modele.getPrixDeVente(cnx);

                StockProduit stockProduit = new StockProduit(modele.getId(),0, pa.getQuantite(), prixRevient, prixVente ,dateAchat);

                GenericDao.Save(cnx, achat);
                GenericDao.Save(cnx, stockProduit);
            }
            cnx.commit();
        }catch(Exception e){
            cnx.rollback();
            e.printStackTrace();
            throw e;
        }

        if(!estAutoCommit){
            cnx.setAutoCommit(true);
        }
        if(closed){
            cnx.close();
        }
    }
}
