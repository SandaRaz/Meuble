package genericServlet;

import cnx.Connex;
import com.google.gson.Gson;
import entity.Modele;
import entityView.ModeleComplet;
import jakarta.servlet.annotation.WebServlet;
import object.SearchParam;
import simpleController.CtrlAnnotation;
import simpleController.MereController;

import java.sql.Connection;
import java.util.List;

@WebServlet(name="SearchController", value="*.SearchController")
public class SearchController extends MereController {
    @CtrlAnnotation(name = "RechercheModeleParPrix")
    public void RechercheModeleParPrix() throws Exception {
        Connection cnx = Connex.PsqlConnect();

        double prixMin = Double.parseDouble(request.getParameter("prixMin"));
        double prixMax = Double.parseDouble(request.getParameter("prixMax"));

        List<ModeleComplet> modeles = new Modele().rechercheByPrix(cnx, prixMin, prixMax);

        request.setAttribute("modeles", modeles);
        cnx.close();

        this.redirect("index.jsp?pagePath=page/gestion/modele/recherches/RecherchePrix.jsp");
    }

    @CtrlAnnotation(name = "RechercheModeleParBenefice")
    public void RechercheModeleParBenefice() throws Exception {
        Connection cnx = Connex.PsqlConnect();

        double beneficeMin = Double.parseDouble(request.getParameter("beneficeMin"));
        double beneficeMax = Double.parseDouble(request.getParameter("beneficeMax"));

        List<ModeleComplet> modeles = new Modele().rechercheByBenefice(cnx, beneficeMin, beneficeMax);

        request.setAttribute("modeles", modeles);
        cnx.close();

        this.redirect("index.jsp?pagePath=page/gestion/modele/recherches/RechercheBenefice.jsp");
    }

    @CtrlAnnotation(name = "RechercheAvance")
    public void RechercheAvance() throws Exception {
        Connection cnx = Connex.PsqlConnect();

        SearchParam searchParam = new SearchParam();

        searchParam.setCheckCategorie(request.getParameter("checkCategorie"));
        searchParam.setIdCategorie(request.getParameter("idcategorie"));

        searchParam.setCheckStyle(request.getParameter("checkStyle"));
        searchParam.setIdStyle(request.getParameter("idstyle"));

        searchParam.setCheckVolume(request.getParameter("checkVolume"));
        searchParam.setVolumeComparaison(request.getParameter("volumeComparaison"));
        searchParam.setEchelleVolume(request.getParameter("echelleVolume"));

        searchParam.setCheckPrix(request.getParameter("checkPrix"));
        searchParam.setPrixComparaison(request.getParameter("prixComparaison"));
        searchParam.setPrix(request.getParameter("prix"));

        searchParam.setCheckBenefice(request.getParameter("checkBenefice"));
        searchParam.setBeneficeComparaison(request.getParameter("beneficeComparaison"));
        searchParam.setBenefice(request.getParameter("benefice"));

        List<ModeleComplet> modeleComplets = new Modele().rechercheAvance(cnx, searchParam);
        cnx.close();

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(modeleComplets);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}
