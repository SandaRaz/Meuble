/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genericServlet;

import cnx.Connex;
import com.google.gson.Gson;
import dao.GenericDao;
import entity.Client;
import entity.Modele;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import object.Panier;
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
@WebServlet(name = "ClientController", value = "*.ClientController")
public class ClientController extends MereController {

    @CtrlAnnotation(name = "InsertClient")
    public void InsertClient() throws SQLException, Exception {
        Connection cnx = Connex.PsqlConnect();

        String nom = request.getParameter("nomClient");
        int idGenre = Integer.parseInt(request.getParameter("idGenre"));

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

        String newIdEmp = Connex.createId(cnx, "client_sequence", "CLI", 7);

        Client client = new Client(newIdEmp, nom,dateNaissance, idGenre);

        GenericDao.Save(cnx, client);

        cnx.close();
        this.redirect("index.jsp?pagePath=page/gestion/client/AjoutClient.jsp");
    }

    @CtrlAnnotation(name="FaireAchat")
    public void FaireAchat() throws Exception{
        Connection cnx = Connex.PsqlConnect();

        String idClient = request.getParameter("idClient");
        List<Object> objects = GenericDao.FindById(cnx, new Client(idClient));
        Client client = null;
        if(!objects.isEmpty()){
            client = (Client) objects.get(0);
        }else{
            throw new Exception("Client non trouvé: "+idClient);
        }

        Panier panier = new Panier(client);
        HttpSession session = request.getSession();
        session.setAttribute("panierClient", panier);

        cnx.close();

        this.redirect("index.jsp?pagePath=page/gestion/client/PanierClient.jsp");
    }

    @CtrlAnnotation(name="AjouterAuPanier")
    public void AjouterAuPanier() throws Exception{
        Connection cnx = Connex.PsqlConnect();

        String idModele = request.getParameter("idModele");
        String quantiteString = request.getParameter("quantite");
        String dateAchat = request.getParameter("dateAchat");
        System.out.println("Date de l'achat: "+dateAchat);
        System.out.println("New Date: "+new Date());

        double quantite = 0;
        try{
            quantite = Double.parseDouble(quantiteString);
        }catch(NumberFormatException nfe){
            throw new Exception("Quantite entree non valide");
        }

        List<Object> objectModeles = GenericDao.FindById(cnx, new Modele(idModele));
        Modele modele = null;
        if(!objectModeles.isEmpty()){
            modele = (Modele) objectModeles.get(0);
        }
        if(modele == null){
            throw new Exception("Modele not found, idModele "+idModele);
        }

        HttpSession session = request.getSession();
        Panier panier = null;
        if(session.getAttribute("panierClient") != null){
            panier = (Panier) session.getAttribute("panierClient");
        }else{
            throw new Exception("Session du panierClient non trouvee");
        }

        try{
            panier.addProduit(cnx, modele, quantite);
            panier.setDateAchat(dateAchat);
        }catch (Exception e){
            cnx.close();

            request.setAttribute("stockManquante", e.getMessage());
            this.redirect("index.jsp?pagePath=page/gestion/client/PanierClient.jsp");
        }

        cnx.close();
        session.setAttribute("panierClient", panier);
        this.redirect("index.jsp?pagePath=page/gestion/client/PanierClient.jsp");
    }

    @CtrlAnnotation(name="Acheter")
    public void Acheter() throws Exception{
        HttpSession session = request.getSession();
        Panier panier = null;
        if(session.getAttribute("panierClient") != null){
            panier = (Panier) session.getAttribute("panierClient");
        }else{
            throw new Exception("Session du panierClient non trouvee");
        }

        Connection cnx = Connex.PsqlConnect();

        new Client().Acheter(cnx, panier);

        cnx.close();

        Panier newPanier = new Panier(panier.getProprietaire());
        session.setAttribute("panierClient", newPanier);
        this.redirect("index.jsp?pagePath=page/gestion/client/PanierClient.jsp");
    }

    @CtrlAnnotation(name="EffacerPanierProduit")
    public void EffacerPanierProduit() throws Exception{
        HttpSession session = request.getSession();
        Panier panier = null;
        if(session.getAttribute("panierClient") != null){
            panier = (Panier) session.getAttribute("panierClient");
        }else{
            throw new Exception("Session du panierClient non trouvee");
        }

        String idModele = request.getParameter("idModele");

        panier.removeProduit(idModele);

        session.setAttribute("panierClient", panier);

        String jsonPanier = new Gson().toJson(panier);

        response.setContentType("application/json");
        response.getWriter().write(jsonPanier);
    }

    @CtrlAnnotation(name="QuitterAchat")
    public void QuitterAchat() throws Exception{
        HttpSession session = request.getSession();

        Object objPanier = session.getAttribute("panierClient");
        if(objPanier != null){
            session.removeAttribute("panierClient");
        }

        this.redirect("index.jsp?pagePath=page/gestion/client/AchatClient.jsp");
    }
}
