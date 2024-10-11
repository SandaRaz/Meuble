package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Modele;
import entityView.ModeleComplet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewDeleteModele {
    public List<ModeleComplet> modeleCompletList = new ArrayList<>();
    public List<Modele> modeleSupprimes = new ArrayList<>();

    public ViewDeleteModele() {
        Connection cnx = Connex.PsqlConnect();

        try {
            modeleCompletList = new ModeleComplet().getAllByOrder(cnx);
            List<Object> objModeles = GenericDao.FindAll(cnx, new Modele(), "statut", 0);
            for(Object obj : objModeles){
                modeleSupprimes.add((Modele) obj);
            }

            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
