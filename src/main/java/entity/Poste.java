/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import cnx.Connex;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DIVA
 */
public class Poste {
    private int id;
    private String titre;
    private double salaireHeure;
    private int niveauTauxHoraire;

    private int hierarchie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getSalaireHeure() {
        return salaireHeure;
    }

    public void setSalaireHeure(double salaireHeure) {
        this.salaireHeure = salaireHeure;
    }

    public int getNiveauTauxHoraire() {
        return niveauTauxHoraire;
    }

    public void setNiveauTauxHoraire(int niveauTauxHoraire) {
        this.niveauTauxHoraire = niveauTauxHoraire;
    }

    public int getHierarchie() {
        return hierarchie;
    }

    public void setHierarchie(int hierarchie) {
        this.hierarchie = hierarchie;
    }

    public Poste() {
    }

    public Poste(int id) {
        this.id = id;
    }

    public Poste(int id, String titre, double salaireHeure, int niveauTauxHoraire, int hierarchie) {
        this.id = id;
        this.titre = titre;
        this.salaireHeure = salaireHeure;
        this.niveauTauxHoraire = niveauTauxHoraire;
        this.hierarchie = hierarchie;
    }

    public Poste(String titre, double salaireHeure, int niveauTauxHoraire, int hierarchie) {
        this.titre = titre;
        this.salaireHeure = salaireHeure;
        this.niveauTauxHoraire = niveauTauxHoraire;
        this.hierarchie = hierarchie;
    }

    public boolean exist(Connection cnx) throws Exception{
        boolean exist = false;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM Poste WHERE titre='"+this.getTitre();
        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql)){
                if(res.next()){
                    exist = true;
                }
            }
        
        if(isclosed){
            cnx.close();
        }
        return exist;
    }

    public static List<Poste> getPosteOuvrier(Connection cnx) throws Exception{
        List<Poste> result = new ArrayList<>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT * FROM Poste WHERE hierarchie=1 ";
        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql)){
            while (res.next()){
                int id = res.getInt("id");
                String titre = res.getString("titre");
                double salaireHeure = res.getDouble("salaireHeure");
                int niveauTauxHoraire = res.getInt("niveauTauxHoraire");
                int hierarchie = res.getInt("hierarchie");

                result.add(new Poste (id,titre,salaireHeure,niveauTauxHoraire,hierarchie));

            }
        }

        if(isclosed){
            cnx.close();
        }
        return result;
    }

    public static int getIdDePromotion(Connection cnx, double dureeTravail) throws SQLException {
        int idPoste = -1;

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT id FROM Poste WHERE DureeMin <= "+dureeTravail+" AND DureeMax > "+dureeTravail;
        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql)){
            while(res.next()){
                idPoste = res.getInt("id");
            }
        }

        if(isclosed){
            cnx.close();
        }

        return idPoste;
    }
}
