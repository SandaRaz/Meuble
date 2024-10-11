/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import cnx.Connex;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DIVA
 */
public class Marge {
    private int id;
    private double prixMin;
    private double prixMax;
    private double pourcentage;

    public Marge() {
    }

    public Marge(int id, double prixMin, double prixMax, double pourcentage) {
        this.id = id;
        this.prixMin = prixMin;
        this.prixMax = prixMax;
        this.pourcentage = pourcentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }

    public double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    
    public static double getPourcentage(Connection cnx, double prix) throws Exception{
        double result = 0;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT pourcentage FROM Marge WHERE prixmin <= "+prix+" AND prixmax >= "+prix;
        Statement stmt = cnx.createStatement();
        System.out.println("SQL >>> "+sql);
        try(ResultSet res = stmt.executeQuery(sql);){
            while(res.next()){
                result = res.getDouble("pourcentage");
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return result;
    }
    
}
