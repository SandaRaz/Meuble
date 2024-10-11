package genericServlet;

import java.sql.Connection;

import association.Matierestyle;
import association.ModeleEmployes;
import association.QuantiteMatiereModele;
import cnx.Connex;
import dao.GenericDao;
import entity.*;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpSession;
import simpleController.MereController;
import simpleController.CtrlAnnotation;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "FormController", value = "*.FormController")
public class FormController extends MereController{
    
    @CtrlAnnotation(name = "AjoutCategorie")
    public void AjoutCategorie() throws SQLException, ServletException, IOException{
        String nom = request.getParameter("nom");
        
        Connection cnx = Connex.PsqlConnect();
        
        Categorie categorie = new Categorie(nom);
        try {
            if(!categorie.exist(cnx)){
                GenericDao.Save(cnx, categorie);
            }
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cnx.close();
        
        this.redirect("index.jsp?pagePath=page/gestion/categorie/AjoutCategorie.jsp");
    }
    
    @CtrlAnnotation(name = "AjoutMatiere")
    public void AjoutMatiere() throws SQLException, ServletException, IOException{
        String nom = request.getParameter("nom");
        double prix = Double.parseDouble(request.getParameter("prix"));
        
        Connection cnx = Connex.PsqlConnect();
        
        Matiere matiere = new Matiere(nom, prix);
        try {
            if(!matiere.exist(cnx)){
                GenericDao.Save(cnx, matiere);
            }
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cnx.close();
        
        this.redirect("index.jsp?pagePath=page/gestion/matierePremiere/AjoutMatiere.jsp");
    }
    
    
    @CtrlAnnotation(name = "AjoutStyle")
    public void AjoutStyle() throws SQLException, ServletException, IOException{
        String nom = request.getParameter("nom");
        
        Connection cnx = Connex.PsqlConnect();
        
        Style style = new Style(nom);
        try {
            if(!style.exist(cnx)){
                GenericDao.Save(cnx, style);
            }
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cnx.close();
        
        this.redirect("index.jsp?pagePath=page/gestion/style/AjoutStyle.jsp");
    }
    
    @CtrlAnnotation(name = "MatieresStyle")
    public void MatieresStyle() throws Exception{
        int idstyle = Integer.parseInt(request.getParameter("idstyle"));
        String[] idmatieres = request.getParameterValues("idmatieres");
        
        Connection cnx = Connex.PsqlConnect();
        cnx.setAutoCommit(false);
        
        for(String idmatiere : idmatieres){
            Matierestyle matiereStyle = new Matierestyle(idstyle, Integer.parseInt(idmatiere));
            try {
                if(!matiereStyle.exist(cnx)){
                    GenericDao.Save(cnx, matiereStyle);
                }
            } catch (Exception ex) {
                cnx.rollback();
                Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        cnx.commit();
        cnx.close();
        
        this.redirect("index.jsp?pagePath=page/gestion/style/AjoutMatiereStyle.jsp");
    }

    @CtrlAnnotation(name = "AjoutVolume")
    public void AjoutVolume() throws SQLException, ServletException, IOException{
        String nom = request.getParameter("nom");
        int echelle = Integer.parseInt(request.getParameter("echelle"));

        Connection cnx = Connex.PsqlConnect();

        Volume volume = new Volume(nom, echelle);
        try {
            if(!volume.exist(cnx)){
                GenericDao.Save(cnx, volume);
            }
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        cnx.close();

        this.redirect("index.jsp?pagePath=page/gestion/volume/AjoutVolume.jsp");
    }
    
    @CtrlAnnotation(name = "AjoutPoste")
    public void AjoutPoste() throws SQLException, ServletException, IOException{
        String titre = request.getParameter("nom");
        double salaireHeure = Double.parseDouble(request.getParameter("salaireHeure"));
        int niveauTH = Integer.parseInt(request.getParameter("niveauTH"));
        int hierarchie = Integer.parseInt(request.getParameter("hierarchie"));

        Connection cnx = Connex.PsqlConnect();

        Poste poste = new Poste(titre,salaireHeure,niveauTH,hierarchie);
        try {
            if(!poste.exist(cnx)){
                GenericDao.Save(cnx, poste);
            }
        } catch (Exception ex) {
            Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        cnx.close();

        this.redirect("index.jsp?pagePath=page/gestion/employe/AjoutPoste.jsp");
    }
    
    @CtrlAnnotation(name = "AjoutModeleVersPage2")
    public void AjoutModeleVersPage2() throws Exception {
        
        String nommodele = request.getParameter("nomModele");
        int idcategorie = Integer.parseInt(request.getParameter("idcategorie"));
        int idstyle = Integer.parseInt(request.getParameter("idstyle"));
        int idvolume = Integer.parseInt(request.getParameter("idvolume"));
        
        System.out.println("REQUEST PARAMETER ID, Categorie: "+idcategorie+" Style: "+idstyle+" Volume: "+idvolume);

        Connection cnx = Connex.PsqlConnect();

        String newId = Connex.createId(cnx, "modele_sequence", "MOD", 7);
        Modele modele = new Modele(newId, nommodele, idcategorie, idstyle, idvolume, 1);
        HttpSession modelSession = request.getSession();

        cnx.close();

        modelSession.setAttribute("tempModele", modele);
        this.redirect("index.jsp?pagePath=page/gestion/modele/AjoutModelePage2.jsp");
    }

    @CtrlAnnotation(name = "InsererModele")
    public void InsererModele() throws Exception {
        Object tempObject = request.getSession().getAttribute("tempModele");
        if(tempObject != null){
            Modele modele = (Modele) tempObject;

            String[] idmatieres = request.getParameterValues("idmatiere");
            String[] quantiteMatiere = request.getParameterValues("quantiteMatiere");

            String[] idpostes = request.getParameterValues("idposte");
            String[] nombres = request.getParameterValues("nombre");
            String[] minHeureTravails = request.getParameterValues("minHeureTravail");

            List<QuantiteMatiereModele> quantsMatsMods = new ArrayList<>();
            for(int i=0 ; i<idmatieres.length; i++){
                quantsMatsMods.add(new QuantiteMatiereModele(modele.getId(), Integer.parseInt(idmatieres[i]), Double.parseDouble(quantiteMatiere[i])));
            }

            List<ModeleEmployes> modeleEmployes = new ArrayList<>();
            for(int i=0 ; i<idpostes.length ; i++){
                modeleEmployes.add(new ModeleEmployes(modele.getId(), Integer.parseInt(idpostes[i]), Integer.parseInt(nombres[i]), Double.parseDouble(minHeureTravails[i])));
            }

            Connection cnx = Connex.PsqlConnect();
            cnx.setAutoCommit(false);

            try{
                if(!modele.exist(cnx)){
                    GenericDao.Save(cnx, modele);
                    for (QuantiteMatiereModele qmm : quantsMatsMods){
                        GenericDao.Save(cnx, qmm);
                    }
                    for(ModeleEmployes me : modeleEmployes){
                        GenericDao.Save(cnx, me);
                    }
                }
                cnx.commit();
                
                /* ----- Fermer la session tempModele apres insertion ----- */
                HttpSession modelSession = request.getSession();
                modelSession.removeAttribute("tempModele");
            }catch (SQLException e){
                cnx.rollback();
                throw e;
            }

            cnx.close();
        }else{
            throw new Exception("TempModele du session null !");
        }

        this.redirect("index.jsp?pagePath=page/gestion/modele/AjoutModele.jsp");
    }

    @CtrlAnnotation(name = "DeleteModele")
    public void DeleteModele() throws Exception {
        String idModele = request.getParameter("idModele");

        Connection cnx = Connex.PsqlConnect();

        List<Object> objModele = GenericDao.FindById(cnx, new Modele(idModele));
        Modele modele = null;
        if(!objModele.isEmpty()){
            modele = (Modele) objModele.get(0);
        }

        if(modele != null){
            modele.setStatut(0);
            int ligneEffacee = GenericDao.Update(cnx, modele);
        }

        this.redirect("index.jsp?pagePath=page/gestion/modele/DeleteModele.jsp");
    }

    @CtrlAnnotation(name = "RestoreModele")
    public void RestoreModele() throws Exception {
        String idModele = request.getParameter("idModele");

        Connection cnx = Connex.PsqlConnect();

        List<Object> objModele = GenericDao.FindById(cnx, new Modele(idModele));
        Modele modele = null;
        if(!objModele.isEmpty()){
            modele = (Modele) objModele.get(0);
        }

        if(modele != null){
            modele.setStatut(1);
            int ligneEffacee = GenericDao.Update(cnx, modele);
        }

        this.redirect("index.jsp?pagePath=page/gestion/modele/CorbeilleModele.jsp");
    }
    
}
