/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichageData;

import java.sql.Connection;
import cnx.Connex;
import dao.GenericDao;
import entity.Modele;
import entityView.ModeleComplet;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sanda
 */
public class ViewModele {
    public List<ViewModeleAvecMatiere> modeleAvecMatiere = new ArrayList<>();
    
    public ViewModele(){
        Connection cnx = Connex.PsqlConnect();
        
        try {
            List<ModeleComplet> modeleComplets = new ModeleComplet().getAllByOrder(cnx);
            for(ModeleComplet mc : modeleComplets){
                modeleAvecMatiere.add(new ViewModeleAvecMatiere(mc));
            }
            
            cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ViewModele.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
