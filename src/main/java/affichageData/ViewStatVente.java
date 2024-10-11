package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Genre;
import entity.Modele;
import entityView.StatistiqueGenre;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ViewStatVente {
    public List<Modele> modeles = new ArrayList<>();
    public List<StatistiqueGenre> statistiqueGenres = new ArrayList<>();

    public ViewStatVente() {
        Connection cnx = Connex.PsqlConnect();

        try {
            List<Object> objModeles = GenericDao.FindAll(cnx, new Modele(), "statut", 0, ">");
            for(Object obj : objModeles){
                modeles.add((Modele) obj);
            }

            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public ViewStatVente(String idModele) {
        Connection cnx = Connex.PsqlConnect();

        try {
            Modele modele = new Modele(idModele);
            modele = (Modele) GenericDao.FindById(cnx, modele.getId()).get(0);
            statistiqueGenres = modele.getModeleStatistique(cnx);

            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
