/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unitTest;

import cnx.Connex;
import genericServlet.FormController;
import dao.GenericDao;
import entity.Categorie;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sanda
 */
public class MainTest {
    public static void main(String[] args){
        String nom = "Chaise";
        
        Connection cnx = Connex.PsqlConnect();
        
        Categorie categorie = new Categorie(nom);
        try {
            GenericDao.Save(cnx, categorie);
            
            cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
}
