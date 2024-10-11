package affichageData;

import cnx.Connex;
import entity.Poste;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ViewAjoutEmploye {
    public List<Poste> postes = new ArrayList<>();

    public ViewAjoutEmploye() {
        Connection cnx = Connex.PsqlConnect();

        try {
            postes = Poste.getPosteOuvrier(cnx);

            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
