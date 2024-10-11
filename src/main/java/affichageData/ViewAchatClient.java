package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Client;
import entity.Genre;
import entity.Modele;
import entityView.ClientComplet;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ViewAchatClient {
    public List<ClientComplet> clients = new ArrayList<>();
    public List<Modele> modeles = new ArrayList<>();

    public ViewAchatClient() {
        Connection cnx = Connex.PsqlConnect();

        try {
            List<Object> objClient = GenericDao.FindAll(cnx, new ClientComplet());
            List<Object> objModele = GenericDao.FindAll(cnx, new Modele(), "statut", 0, ">");
            for(Object obj : objClient){
                clients.add((ClientComplet) obj);
            }
            for(Object obj : objModele){
                modeles.add((Modele) obj);
            }

            cnx.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
