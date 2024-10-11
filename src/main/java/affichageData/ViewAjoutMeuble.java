/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichageData;

import java.sql.Connection;
import cnx.Connex;
import dao.GenericDao;
import entity.*;
import entityView.ModeleComplet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanda
 */
public class ViewAjoutMeuble {
    public List<Categorie> categories = new ArrayList<>();
    public List<Style> styles = new ArrayList<>();
    public List<Volume> volumes = new ArrayList<>();
    public List<Matiere> matieres = new ArrayList<>();
    public ModeleComplet modeleComplet;
    public List<Poste> postes = new ArrayList<>();
    
    public ViewAjoutMeuble(){
        Connection cnx = Connex.PsqlConnect();
        
        try{
            for(Object obj : GenericDao.FindAll(cnx, new Categorie())){
                categories.add((Categorie) obj);
            }
            for(Object obj : GenericDao.FindAll(cnx, new Style())){
                styles.add((Style) obj);
            }
            for(Object obj : GenericDao.FindAll(cnx, new Volume())){
                volumes.add((Volume) obj);
            }

            System.out.println(categories.size()+", "+styles.size()+", "+volumes.size());
            
            cnx.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ViewAjoutMeuble(Modele modele){
        Connection cnx = Connex.PsqlConnect();

        try{
            Style style = (Style) GenericDao.FindById(cnx, new Style(modele.getIdStyle(), "")).get(0);
            for(Matiere mat : style.getMatieres(cnx)){
                matieres.add(mat);
            }
            
            Categorie categorie = (Categorie) GenericDao.FindById(cnx, new Categorie(modele.getIdCategorie(), "")).get(0);
            Volume volume = (Volume) GenericDao.FindById(cnx, new Volume(modele.getIdVolume(), "", 0)).get(0);

            modeleComplet = new ModeleComplet(modele.getNomModele(), categorie,style,volume);
            
            for(Object obj : GenericDao.FindAll(cnx, new Poste())){
                postes.add((Poste) obj);
            }
            
            cnx.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
