/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichageData;

import java.sql.Connection;
import cnx.Connex;
import entityView.ModeleComplet;
import entityView.QuantiteMatiereDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sanda
 */
public class ViewModeleAvecMatiere {
    public ModeleComplet modele;
    public List<QuantiteMatiereDetail> matieres = new ArrayList<>();
    
    public ViewModeleAvecMatiere(ModeleComplet modele){
        this.modele= modele;
        
        Connection cnx = Connex.PsqlConnect();
        
        try {
            this.matieres = this.modele.getListeMatiereDetaillee(cnx);
            
            cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ViewModeleAvecMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
