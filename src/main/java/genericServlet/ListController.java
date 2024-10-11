/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genericServlet;

import cnx.Connex;
import dao.GenericDao;
import entity.Matiere;
import entity.Modele;
import entityView.ModeleComplet;
import entityView.StockProduitDetail;
import jakarta.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.util.List;

import object.Pagination;
import simpleController.CtrlAnnotation;
import simpleController.MereController;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "ListController", value = "*.ListController")
public class ListController extends MereController {
    @CtrlAnnotation(name = "ListMeuble")
    public void ListMeuble(){
        
    }
    
    @CtrlAnnotation(name = "ListModelesMatiere")
    public void ListModelesMatiere() throws Exception {
        Connection cnx = Connex.PsqlConnect();

        int idmatiere = Integer.parseInt(request.getParameter("idmatiere"));
        Matiere matiere = (Matiere) GenericDao.FindById(cnx, new Matiere(idmatiere, "")).get(0);

        List<ModeleComplet> modeles = matiere.getModelesUsingMe(cnx);
        for(ModeleComplet modele : modeles){
            System.out.println("MId: "+modele.getId());
        }
        request.setAttribute("modeles", modeles);
        cnx.close();

        this.redirect("index.jsp?pagePath=page/gestion/matierePremiere/ListModeleMatiere.jsp");
    }

    @CtrlAnnotation(name = "MvtDeStockGlobale")
    public void MvtDeStockGlobale() throws Exception {
        Connection cnx = Connex.PsqlConnect();

        int offset = 0;
        int limit = 5;

        List<StockProduitDetail> stockProduits = new Modele().getListMouvementGlobale(cnx, offset, limit);

        int totaleLigne = new Modele().getNombreMouvementGlobale(cnx);
        int nombrePage = (int) Math.ceil((double) totaleLigne / limit);

        Pagination pagination = new Pagination(offset,limit,totaleLigne,nombrePage, 1);

        request.setAttribute("stockProduits", stockProduits);
        request.setAttribute("pagination", pagination);

        cnx.close();
        this.redirect("index.jsp?pagePath=page/gestion/stock/EtatGlobaleStock.jsp");
    }

    @CtrlAnnotation(name = "StockGlobaleNextPage")
    public void StockGlobaleNextPage() throws Exception {
        Connection cnx = Connex.PsqlConnect();

        int offset = Integer.parseInt(request.getParameter("offset"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        int displayed = 5;
        int pageActuel = Integer.parseInt(request.getParameter("pageActuel"));

        List<StockProduitDetail> stockProduits = new Modele().getListMouvementGlobale(cnx, offset, limit);

        int totaleLigne = new Modele().getNombreMouvementGlobale(cnx);
        int nombrePage = (int) Math.ceil((double) totaleLigne / displayed);

        Pagination pagination = new Pagination(offset,displayed,totaleLigne,nombrePage, pageActuel);

        request.setAttribute("stockProduits", stockProduits);
        request.setAttribute("pagination", pagination);

        cnx.close();

        this.redirect("index.jsp?pagePath=page/gestion/stock/EtatGlobaleStock.jsp");
    }

    @CtrlAnnotation(name = "MvtDeStockModele")
    public void MvtDeStockModele() throws Exception {
        Connection cnx = Connex.PsqlConnect();

        String idModele = request.getParameter("idModele");
        List<Object> objModeles = GenericDao.FindById(cnx, new Modele(idModele));
        Modele modele = new Modele();
        if(!objModeles.isEmpty()){
            modele = (Modele) objModeles.get(0);
        }

        int offset = 0;
        int limit = 5;

        List<StockProduitDetail> stockProduits = modele.getListMouvement(cnx, offset, limit);
        double resteEnStock = modele.getResteEnStock(cnx);
        double valeurStock = modele.getValeurStock(cnx);

        int totaleLigne = modele.getNombreMouvement(cnx);
        int nombrePage = (int) Math.ceil((double) totaleLigne / limit);

        Pagination pagination = new Pagination(offset,limit,totaleLigne,nombrePage, 1);

        request.setAttribute("idModele", idModele);
        request.setAttribute("stockProduits", stockProduits);
        request.setAttribute("resteEnStock", resteEnStock);
        request.setAttribute("valeurStock", valeurStock);
        request.setAttribute("pagination", pagination);

        cnx.close();
        this.redirect("index.jsp?pagePath=page/gestion/stock/EtatModele.jsp");
    }

    @CtrlAnnotation(name = "StockModeleNextPage")
    public void StockModeleNextPage() throws Exception {
        Connection cnx = Connex.PsqlConnect();

        String idModele = request.getParameter("idModele");
        Modele modele = (Modele) GenericDao.FindById(cnx, new Modele(idModele)).get(0);

        int offset = Integer.parseInt(request.getParameter("offset"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        int displayed = 5;
        int pageActuel = Integer.parseInt(request.getParameter("pageActuel"));

        List<StockProduitDetail> stockProduits = modele.getListMouvement(cnx, offset, limit);
        double resteEnStock = modele.getResteEnStock(cnx);
        double valeurStock = modele.getValeurStock(cnx);

        int totaleLigne = modele.getNombreMouvement(cnx);
        int nombrePage = (int) Math.ceil((double) totaleLigne / displayed);

        Pagination pagination = new Pagination(offset,displayed,totaleLigne,nombrePage, pageActuel);

        request.setAttribute("idModele", idModele);
        request.setAttribute("stockProduits", stockProduits);
        request.setAttribute("resteEnStock", resteEnStock);
        request.setAttribute("valeurStock", valeurStock);
        request.setAttribute("pagination", pagination);

        cnx.close();

        this.redirect("index.jsp?pagePath=page/gestion/stock/EtatModele.jsp");
    }
}
