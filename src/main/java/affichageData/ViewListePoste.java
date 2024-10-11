package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Poste;
import entityView.ModeleAvecPrix;
import entityView.StockProduitDetail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ViewListePoste {
    public List<Poste> postes = new ArrayList<>();

    public ViewListePoste() {
        Connection cnx = Connex.PsqlConnect();

        try {
            List<Object> objects = GenericDao.FindAll(cnx, new Poste());
            for(Object o : objects){
                Poste p = (Poste) o;
                this.postes.add(p);
            }
            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
