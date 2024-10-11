package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entityView.InfosEmploye;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ViewListeEmploye {
    public List<InfosEmploye> employes = new ArrayList<>();

    public ViewListeEmploye(){
        Connection cnx = Connex.PsqlConnect();

        try {
            List<Object> objEmp = GenericDao.FindAll(cnx, new InfosEmploye());
            for(Object obj : objEmp){
                employes.add((InfosEmploye) obj);
            }

            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
