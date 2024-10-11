/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Matiere;
import entity.Style;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanda
 */
public class ViewMatiereStyle {
    public List<Style> styles = new ArrayList<>();
    public List<Matiere> matieres = new ArrayList<>();
    
    public ViewMatiereStyle(){
        Connection cnx = Connex.PsqlConnect();
        
        try{
            for(Object obj : GenericDao.FindAll(cnx, new Style())){
                styles.add((Style) obj);
            }
            for(Object obj : GenericDao.FindAll(cnx, new Matiere())){
                matieres.add((Matiere) obj);
            }
            
            cnx.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
