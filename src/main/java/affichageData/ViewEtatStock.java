package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Modele;
import entityView.StatistiqueGenre;
import entityView.StockProduitDetail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ViewEtatStock {
    public List<Modele> modeles = new ArrayList<>();

    public ViewEtatStock() {
        Connection cnx = Connex.PsqlConnect();

        try {
            List<Object> objModeles = GenericDao.FindAll(cnx, new Modele(), "statut", 0, ">");
            for(Object obj : objModeles){
                this.modeles.add((Modele) obj);
            }

            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
