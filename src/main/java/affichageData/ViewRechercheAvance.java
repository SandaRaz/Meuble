package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Categorie;
import entity.Style;
import entity.Volume;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewRechercheAvance {
    public List<Categorie> categories = new ArrayList<>();
    public List<Style> styles = new ArrayList<>();
    public List<Volume> volumes = new ArrayList<>();

    public ViewRechercheAvance(){
        Connection cnx = Connex.PsqlConnect();

        try {
            List<Object> objects = GenericDao.FindAll(cnx, new Categorie());
            for(Object obj : objects){
                categories.add((Categorie) obj);
            }

            objects = GenericDao.FindAll(cnx, new Style());
            for(Object obj : objects){
                styles.add((Style) obj);
            }

            objects = GenericDao.FindAll(cnx, new Volume());
            for(Object obj : objects){
                volumes.add((Volume) obj);
            }

            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
