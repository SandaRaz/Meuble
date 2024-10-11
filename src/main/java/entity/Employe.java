package entity;

import association.HistoriqueEmbauche;
import cnx.Connex;
import dao.GenericDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Employe {
    private String id;
    private String nom;
    private Date dateNaissance;
    private int idPosteActuelle;

    public Employe() {
    }

    public Employe(String id, String nom, Date dateNaissance, int idPosteActuelle) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.idPosteActuelle = idPosteActuelle;
    }

    public Employe(String nom, Date dateNaissance, int idPosteActuelle) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.idPosteActuelle = idPosteActuelle;
    }

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

    public int getIdPosteActuelle() {
        return idPosteActuelle;
    }

    public void setIdPosteActuelle(int idPosteActuelle) {
        this.idPosteActuelle = idPosteActuelle;
    }

    public double getDureeDeTravail(Connection cnx) throws Exception{
        double result = -1;

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT id,idEmploye, (EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM dateDebut)) as dureeTravail FROM HistoriqueEmbauche WHERE id = (SELECT MIN(id) as id FROM HistoriqueEmbauche WHERE idEmploye = '"+ this.getId() +"')";
        System.out.println("SQL >>> "+sql);

        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql);){
            if (res.next()){
                result = res.getDouble("dureeTravail");
            }
        }

        if(isclosed){
            cnx.close();
        }
        return result;
    }

    public boolean doitEtrePromus(Connection cnx) throws Exception {
        boolean rep = false;

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        int idPosteDePromotion = Poste.getIdDePromotion(cnx, this.getDureeDeTravail(cnx));
        System.out.println("ID du poste de promotion: "+idPosteDePromotion);
        System.out.println("ID du poste actuelle: "+this.getIdPosteActuelle());

        if(idPosteDePromotion != this.getIdPosteActuelle() && idPosteDePromotion != -1){
            rep = true;
        }

        if(isclosed){
            cnx.close();
        }

        return rep;
    }

    public double getTauxHoraireOuvrier(Connection cnx,String idEmploye) throws Exception{
        double result = -1;

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT id,idEmploye, tauxHoraire FROM HistoriqueEmbauche WHERE id = (SELECT MIN(id) as id FROM HistoriqueEmbauche WHERE idEmploye = '"+ idEmploye +"')";
        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql);){
            if (res.next()){
                result = res.getDouble("tauxHoraire");
            }
        }

        if(isclosed){
            cnx.close();
        }
        return result;
    }

    public void Promotion(Connection cnx, int idPoste) throws Exception {
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        Poste poste = (Poste) GenericDao.FindById(cnx, new Poste(idPoste)).get(0);

        Employe emp = new Employe(this.getId(), this.getNom(),this.getDateNaissance(), poste.getId());
        double tauxHoraireOuvrier = getTauxHoraireOuvrier(cnx, this.getId());
        double nouveauTauxHoraire = tauxHoraireOuvrier * poste.getNiveauTauxHoraire();

        HistoriqueEmbauche he = new HistoriqueEmbauche(this.getId(),poste.getId(),nouveauTauxHoraire,new Date());

        cnx.setAutoCommit(false);
        try{
            GenericDao.Update(cnx, emp);
            GenericDao.Save(cnx, he);

            cnx.commit();
        }catch(Exception e){
            cnx.rollback();
            e.printStackTrace();
        }

        cnx.setAutoCommit(true);

        if(isclosed){
            cnx.close();
        }
    }
}
