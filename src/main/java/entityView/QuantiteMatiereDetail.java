/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entityView;

import cnx.Connex;
import entity.Matiere;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Sanda
 */
public class QuantiteMatiereDetail extends Matiere{
    private int id;
    private String idModele;
    private int idMatiere;
    private String nommatiere;
    private double prix;
    private double quantite;

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

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNommatiere() {
        return nommatiere;
    }

    public void setNommatiere(String nommatiere) {
        this.nommatiere = nommatiere;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public QuantiteMatiereDetail(int id, String idModele, int idMatiere, String nommatiere, double prix, double quantite) {
        this.id = id;
        this.idModele = idModele;
        this.idMatiere = idMatiere;
        this.nommatiere = nommatiere;
        this.prix = prix;
        this.quantite = quantite;
    }

    public QuantiteMatiereDetail(String idModele, int idMatiere, String nommatiere, double prix, double quantite) {
        this.idModele = idModele;
        this.idMatiere = idMatiere;
        this.nommatiere = nommatiere;
        this.prix = prix;
        this.quantite = quantite;
    }
    
    @Override
    public double getResteEnStock(Connection cnx) throws Exception{
        double reste = -1;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT (sum(entrer)-sum(sortie)) as reste FROM MvtStockMatiere WHERE idMatiere="+this.idMatiere;
        Statement stmt = cnx.createStatement();
        System.out.println("SQL >>> "+sql);
        try(ResultSet res = stmt.executeQuery(sql)){
            if(res.next()){
                reste = res.getDouble("reste");
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return reste;
    }
}
