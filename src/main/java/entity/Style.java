package entity;


import cnx.Connex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Style {
	private int id; 
	private String nomstyle; 
    public int getId(){ 
        return this.id; 
    } 
    public void setId(int id){ 
        this.id = id; 
    } 
 
    public String getNomstyle(){ 
        return this.nomstyle; 
    } 
    public void setNomstyle(String nomstyle){ 
        this.nomstyle = nomstyle; 
    } 

    public Style() {
    }

    public Style(String nomstyle) {
        this.nomstyle = nomstyle;
    }

    public Style(int id, String nomstyle) {
        this.id = id;
        this.nomstyle = nomstyle;
    }

    public List<Matiere> getMatieres(Connection cnx) throws SQLException {
        List<Matiere> matieres = new ArrayList<>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT * FROM MatiereStyleComplet WHERE idStyle="+this.getId();
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        Matiere matiere = null;
        while(res.next()){
            matiere = new Matiere(res.getInt("idmatiere"), res.getString("nommatiere"));
            matieres.add(matiere);
        }

        if(isclosed){
            cnx.close();
        }

        return matieres;
    }
    
    public boolean exist(Connection cnx) throws Exception{
        boolean exist = false;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM Style WHERE nomstyle = '"+this.getNomstyle()+"'";
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