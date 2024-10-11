package association;
 
import cnx.Connex;
import java.sql.*;
 
public class Matierestyle {
	private int id; 
	private int idstyle; 
	private int idmatiere; 
    public int getId(){ 
        return this.id; 
    } 
    public void setId(int id){ 
        this.id = id; 
    } 
 
    public int getIdstyle(){ 
        return this.idstyle; 
    } 
    public void setIdstyle(int idstyle){ 
        this.idstyle = idstyle; 
    } 
 
    public int getIdmatiere(){ 
        return this.idmatiere; 
    } 
    public void setIdmatiere(int idmatiere){ 
        this.idmatiere = idmatiere; 
    } 

    public Matierestyle(int idstyle, int idmatiere) {
        this.idstyle = idstyle;
        this.idmatiere = idmatiere;
    }

    public Matierestyle() {
    }
    
    public boolean exist(Connection cnx) throws Exception{
        boolean exist = false;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM MatiereStyle WHERE idstyle='"+this.getIdstyle()+"' AND idmatiere='"+this.getIdmatiere()+"'";
        Statement stmt = cnx.createStatement();
        System.out.println("SQL >>> "+sql);
        try(ResultSet res = stmt.executeQuery(sql);){
            if(res.next()){
                exist = true;
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return exist;
    }
    
}