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
import java.util.Date;
/**
 *
 * @author DIVA
 */
public class MvtStockMatiere {
    private int id;
    private int idMatiere;
    private double entrer;
    private double sortie;
    private Date dateMvt;

    public MvtStockMatiere() {}
    
    public MvtStockMatiere(int id, int idMatiere, double entrer, double sortie, Date dateMvt) {
        this.id = id;
        this.idMatiere = idMatiere;
        this.entrer = entrer;
        this.sortie = sortie;
        this.dateMvt = dateMvt;
    }
    
    public MvtStockMatiere(int idMatiere, double entrer, double sortie, Date dateMvt) {
        this.idMatiere = idMatiere;
        this.entrer = entrer;
        this.sortie = sortie;
        this.dateMvt = dateMvt;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
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

    public Date getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(Date dateMvt) {
        this.dateMvt = dateMvt;
    }
    
    public int Stockage(Connection cnx) throws SQLException{
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        int ligneInseree = 0;
        
        String sql = "INSERT INTO MvtStockMatiere(id,idmatiere,entrer,sortie,datemvt)"
        + " VALUES(default,'"+this.getIdMatiere()+"','"+this.getEntrer()+"','"+this.getSortie()+"','"+this.getDateMvt()+"')";
        
        Statement stmt = cnx.createStatement();
        System.out.println("SQL >>> "+sql);
        
        ligneInseree = stmt.executeUpdate(sql);
        
        if(isclosed){
            cnx.close();
        }
        return ligneInseree;
    }
}


