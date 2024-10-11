package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Client;
import entity.Genre;
import entity.Poste;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ViewAjoutClient {
    public List<Genre> genres = new ArrayList<>();

    public ViewAjoutClient() {
        Connection cnx = Connex.PsqlConnect();

        try {
            List<Object> objGenre = GenericDao.FindAll(cnx, new Genre());
            for(Object obj : objGenre){
                genres.add((Genre) obj);
            }

            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
