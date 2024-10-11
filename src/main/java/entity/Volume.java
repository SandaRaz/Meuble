package entity;

import cnx.Connex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Volume {
    private int id;
    private String nomVolume;
    private int echelle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomVolume() {
        return nomVolume;
    }

    public void setNomVolume(String nomVolume) {
        this.nomVolume = nomVolume;
    }

    public int getEchelle() {
        return echelle;
    }

    public void setEchelle(int echelle) {
        this.echelle = echelle;
    }

    public Volume() {
    }

    public Volume(int id, String nomVolume, int echelle) {
        this.id = id;
        this.nomVolume = nomVolume;
        this.echelle = echelle;
    }

    public Volume(String nomVolume, int echelle) {
        this.nomVolume = nomVolume;
        this.echelle = echelle;
    }
    
    public boolean exist(Connection cnx) throws Exception{
        boolean exist = false;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM Volume WHERE nomvolume = '"+this.getNomVolume()+"'";
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
