package entity;


import cnx.Connex;
import dao.GenericDao;
import entityView.ModeleComplet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Matiere {
	private int id; 
	private String nommatiere; 
        private double prix;
    public int getId(){ 
        return this.id; 
    } 
    public void setId(int id){ 
        this.id = id; 
    } 
 
    public String getNommatiere(){ 
        return this.nommatiere; 
    } 
    public void setNommatiere(String nommatiere){ 
        this.nommatiere = nommatiere; 
    } 
    
    public double getPrix(){ 
        return this.prix; 
    } 
    public void setPrix(double prix){ 
        this.prix = prix; 
    } 

    public Matiere() {
    }

    public Matiere(int id, String nommatiere) {
        this.id = id;
        this.nommatiere = nommatiere;
    }

    public Matiere(String nommatiere, double prix) {
        this.setNommatiere(nommatiere);
        this.setPrix(prix);
    }

    public List<ModeleComplet> getModelesUsingMe(Connection cnx) throws Exception {
        List<ModeleComplet> modeles = new ArrayList<>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT DISTINCT idmodele FROM QuantiteMatiereModele WHERE idmatiere="+this.getId();
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        ModeleComplet modeleComplet = null;
        while(res.next()){
            modeleComplet = new ModeleComplet(res.getString("idmodele"));
            Object tempRes = GenericDao.FindById(cnx, modeleComplet).get(0);
            if(tempRes != null){
                modeles.add((ModeleComplet) tempRes);
            }else{
                throw new Exception("Oject null from getModelsUsingMe FindById");
            }
        }

        if(isclosed){
            cnx.close();
        }
        return modeles;
    }
    
    public boolean exist(Connection cnx) throws Exception{
        boolean exist = false;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM Matiere WHERE nommatiere='"+this.getNommatiere()+"'";
        Statement stmt = cnx.createStatement();
        System.out.println("SQL >>> "+sql);
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
    
    public double getResteEnStock(Connection cnx) throws Exception{
        double reste = -1;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT (sum(entrer)-sum(sortie)) as reste FROM MvtStockMatiere WHERE idMatiere="+this.id;
        Statement stmt = cnx.createStatement();
        System.out.println("SQL >>> "+sql);
        try(ResultSet res = stmt.executeQuery(sql);){
            if(res.next()){
                reste=res.getDouble("reste");
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return reste;
    }
    
    public List<MvtStockMatiere> getMvtDeStock(Connection cnx) throws SQLException{
        List<MvtStockMatiere> mouvements = new ArrayList<>();
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM MvtStockMatiere WHERE idMatiere=?";
        try(PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, this.getId());
            try(ResultSet res = stmt.executeQuery()){
                while(res.next()){
                    MvtStockMatiere mouvement = new MvtStockMatiere(res.getInt("id"), 
                res.getInt("idmatiere"), res.getDouble("entrer"), 
                    res.getDouble("sortie"), res.getDate("datemvt"));
                    
                    mouvements.add(mouvement);
                }
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return mouvements;
    }
    
    public List<MvtStockMatiere> getMvtDesMatieres(Connection cnx) throws SQLException{
        List<MvtStockMatiere> mouvements = new ArrayList<>();
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM MvtStockMatiere WHERE idMatiere=?";
        try(PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, this.getId());
            try(ResultSet res = stmt.executeQuery()){
                while(res.next()){
                    MvtStockMatiere mouvement = new MvtStockMatiere(res.getInt("id"), 
                res.getInt("idmatiere"), res.getDouble("entrer"), 
                    res.getDouble("sortie"), res.getDate("datemvt"));
                    
                    mouvements.add(mouvement);
                }
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return mouvements;
    }
}