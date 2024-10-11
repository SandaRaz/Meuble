package entity; 
 
import cnx.Connex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

 
public class Categorie {
	private int id; 
	private String nom; 
    public int getId(){ 
        return this.id; 
    } 
    public void setId(int id){ 
        this.id = id; 
    } 
 
    public String getNom(){ 
        return this.nom; 
    } 
    public void setNom(String nom){ 
        this.nom = nom; 
    } 

    public Categorie() {
    }
    
    public Categorie(String nom) {
        this.setNom(nom);
    }

    public Categorie(int id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }
    
    
    public boolean exist(Connection cnx) throws Exception{
        boolean exist = false;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM Categorie WHERE nom = '"+this.getNom()+"'";
        Statement stmt = cnx.createStatement();
        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            try(ResultSet res = stmt.executeQuery(sql);){
                if(res.next()){
                    exist = true;
                }
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return exist;
    }
}