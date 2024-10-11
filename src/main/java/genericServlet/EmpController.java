/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genericServlet;

import cnx.Connex;
import dao.GenericDao;
import entity.Employe;
import association.HistoriqueEmbauche;
import entity.Poste;
import jakarta.servlet.annotation.WebServlet;
import simpleController.CtrlAnnotation;
import simpleController.MereController;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sanda
 */
@WebServlet(name = "EmpController", value = "*.EmpController")
public class EmpController extends MereController {
    @CtrlAnnotation(name = "MenuEmployer")
    public void MenuEmployer() throws SQLException, Exception {
        Connection cnx = Connex.PsqlConnect();

        List<Object> empObj = GenericDao.FindAll(cnx, new Employe());
        for(Object obj : empObj){
            Employe emp = (Employe) obj;
            if(emp.doitEtrePromus(cnx)){
                double dureeTravail = emp.getDureeDeTravail(cnx);
                int idNewPoste = Poste.getIdDePromotion(cnx, dureeTravail);
                emp.Promotion(cnx, idNewPoste);
            }
        }

        cnx.close();
        this.redirect("index.jsp?pagePath=page/gestion/employe/MenuEmploye.jsp");
    }

    @CtrlAnnotation(name = "InsertEmployer")
    public void InsertEmployer() throws SQLException, Exception {
        Connection cnx = Connex.PsqlConnect();

        String nom = request.getParameter("nomEmploye");
        int idPoste = Integer.parseInt(request.getParameter("idPoste"));

        String dateNaissanceString = request.getParameter("dateNaissance");

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dateNaissance = null;
        try {
            dateNaissance = inputFormat.parse(dateNaissanceString);
            String dateNaissanceFormatted = outputFormat.format(dateNaissance);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        double tauxHoraire = Double.parseDouble(request.getParameter("tauxHoraire"));

        String newIdEmp = Connex.createId(cnx, "employe_sequence", "EMP", 7);

        Employe emp = new Employe(newIdEmp, nom,dateNaissance,idPoste);
        HistoriqueEmbauche he = new HistoriqueEmbauche(newIdEmp, idPoste,tauxHoraire,new Date());

        cnx.setAutoCommit(false);
        try{
            GenericDao.Save(cnx, emp);
            GenericDao.Save(cnx, he);

            cnx.commit();
        }catch(Exception e){
            cnx.rollback();
            e.printStackTrace();
        }

        cnx.setAutoCommit(true);

        cnx.close();
        this.redirect("index.jsp?pagePath=page/gestion/employe/AjoutEmploye.jsp");
    }
}
