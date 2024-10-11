/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Modele;
import entityView.ModeleComplet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanda
 */
public class ViewFabrication {
    public List<Modele> modeles = new ArrayList<>();
    public ViewFabrication(){
        Connection cnx = Connex.PsqlConnect();

        try{
            for(Object modele : GenericDao.FindAll(cnx, new Modele(), "statut", 0, ">")){
                modeles.add((Modele) modele);
            }

            cnx.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


