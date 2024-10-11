/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import cnx.Connex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 *
 * @author DIVA
 */
public class StockProduit {
    private int id;
    private String idModele;
    private double entrer;
    private double sortie;
    private double prixRevient;
    private double prixVente;
    private Date dateMvt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public double getEntrer() {
        return entrer;
    }

    public void setEntrer(double entrer) {
        this.entrer = entrer;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public double getPrixRevient() {
        return prixRevient;
    }

    public void setPrixRevient(double prixRevient) {
        this.prixRevient = prixRevient;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public Date getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(Date dateMvt) {
        this.dateMvt = dateMvt;
    }

    public StockProduit() {
    }

    public StockProduit(int id, String idModele, double entrer, double sortie, double prixRevient, double prixVente, Date dateMvt) {
        this.id = id;
        this.idModele = idModele;
        this.entrer = entrer;
        this.sortie = sortie;
        this.prixRevient = prixRevient;
        this.prixVente = prixVente;
        this.dateMvt = dateMvt;
    }

    public StockProduit(String idModele, double entrer, double sortie, double prixRevient, double prixVente, Date dateMvt) {
        this.idModele = idModele;
        this.entrer = entrer;
        this.sortie = sortie;
        this.prixRevient = prixRevient;
        this.prixVente = prixVente;
        this.dateMvt = dateMvt;
    }

    public double getReste(Connection cnx, String idModele) throws SQLException {
        boolean closed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            closed = true;
        }

        double reste = 0;

        String sql = "SELECT reste FROM StockEtat WHERE idmodele = ?";
        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            pstmt.setString(1, idModele);
            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                reste = res.getDouble("reste");
            }
        }

        if(closed){
            cnx.close();
        }

        return reste;
    }
}
