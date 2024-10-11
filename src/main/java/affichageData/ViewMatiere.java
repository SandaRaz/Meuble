package affichageData;

import cnx.Connex;
import dao.GenericDao;
import entity.Matiere;
import entityView.MvtStockMatiereDetail;
import entity.Style;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ViewMatiere {
    public List<Matiere> matieres = new ArrayList<>();
    public List<MvtStockMatiereDetail> mvtMatieres = new ArrayList<>();

    public ViewMatiere() {
        Connection cnx = Connex.PsqlConnect();

        try{
            for(Object mat : GenericDao.FindAll(cnx, new Matiere())){
                matieres.add((Matiere) mat);
            }
            for(Object mat : GenericDao.FindAll(cnx, new MvtStockMatiereDetail())){
                mvtMatieres.add((MvtStockMatiereDetail) mat);
            }

            cnx.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
