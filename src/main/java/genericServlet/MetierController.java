/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genericServlet;

import cnx.Connex;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.GenericDao;
import entity.Genre;
import entity.Matiere;
import entity.Modele;
import entity.MvtStockMatiere;
import entityView.ModeleComplet;
import entityView.StatistiqueGenre;
import exception.StockManquante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import simpleController.CtrlAnnotation;
import simpleController.MereController;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "MetierController", value = "*.MetierController")
public class MetierController extends MereController {
    @CtrlAnnotation(name = "StockageMatiere")
    public void StockageMatiere() throws SQLException, Exception{
        Connection cnx = Connex.PsqlConnect();
        
        int idMatiere = Integer.parseInt(request.getParameter("idmatiere"));
        double quantite = Double.parseDouble(request.getParameter("quantite"));
        
        MvtStockMatiere stockage = new MvtStockMatiere(idMatiere,quantite,0,new Date());
        
        int insertion = stockage.Stockage(cnx);
        System.out.println("Stockage insererer: "+insertion);
        
        cnx.close();
        this.redirect("index.jsp?pagePath=page/gestion/matierePremiere/StockageMatiere.jsp");
    }
    
    @CtrlAnnotation(name = "Fabrication")
    public void Fabrication() throws SQLException, ServletException, IOException, Exception{
        Connection cnx = Connex.PsqlConnect();
        
        String idModele = request.getParameter("idmodele");
        double quantite = Double.parseDouble(request.getParameter("quantite"));
        
        try{
            Modele modele = (Modele) GenericDao.FindById(cnx, new Modele(idModele)).get(0);
            modele.fabriquer(cnx, quantite);
        }catch(StockManquante sm){
            cnx.close();
            sm.printStackTrace();
            System.out.println("EXCEPTION EST UN STOCKMANQUANTE");
            request.setAttribute("StockManquante", sm);

            this.redirect("index.jsp?pagePath=page/gestion/modele/Fabrication.jsp");
//            if(e instanceof StockManquante){
//                System.out.println("EXCEPTION EST UN STOCKMANQUANTE");
//                StockManquante sm = (StockManquante) e;
//                request.setAttribute("StockManquante", sm);
//                
//                this.redirect("index.jsp?pagePath=page/gestion/modele/Fabrication.jsp");
//            }
        }
        
        cnx.close();
        this.redirect("index.jsp?pagePath=page/gestion/modele/Fabrication.jsp");
    }

    @CtrlAnnotation(name = "StatGenreModele")
    public void StatGenreModele() throws SQLException, Exception{
        Connection cnx = Connex.PsqlConnect();

        String idModele = request.getParameter("idModele");
        System.out.println("IDMODELE >>> "+idModele);

        Modele modele = new Modele(idModele);
        modele = (Modele) GenericDao.FindById(cnx, modele).get(0);
        List<StatistiqueGenre> statistiqueGenres = modele.getModeleStatistique(cnx);

        request.setAttribute("statsGenres", statistiqueGenres);

        cnx.close();
        this.redirect("index.jsp?pagePath=page/gestion/modele/StatVente.jsp");
    }

    @CtrlAnnotation(name = "afficheDonut")
    public void afficheDonut() throws SQLException, Exception{
        Connection cnx = Connex.PsqlConnect();

        String idModele = request.getParameter("idModele");
        System.out.println("IDMODELE >>> "+idModele);

        Modele modele = new Modele(idModele);
        modele = (Modele) GenericDao.FindById(cnx, modele).get(0);
        List<StatistiqueGenre> statistiqueGenres = modele.getModeleStatistique(cnx);

        JsonObject jsonObject = new JsonObject();
        for(StatistiqueGenre stats : statistiqueGenres){
            Genre genre = (Genre) GenericDao.FindById(cnx, new Genre(stats.getIdGenre())).get(0);
            jsonObject.addProperty(genre.getType(), stats.getQuantite());
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(jsonObject);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}
