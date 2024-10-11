package object;

import cnx.Connex;
import entity.Client;
import entity.Modele;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Panier {
    Client proprietaire;
    List<ProduitAchetee> listeProduits = new ArrayList<>();
    Date dateAchat;

    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }

    public List<ProduitAchetee> getListeProduits() {
        return listeProduits;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(dateString.isBlank()){
            dateString = sdf.format(new Date());
        }

        try {
            this.dateAchat = sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Panier() {
    }
    public Panier(Client proprietaire) {
        this.proprietaire = proprietaire;
    }
    public Panier(Client proprietaire, String dateAchat) {
        this.setProprietaire(proprietaire);
        this.setDateAchat(dateAchat);
    }

    public void Display(){
        System.out.println("------------ PANIER DE "+this.getProprietaire().getNom()+" -----------");
        for(ProduitAchetee pa : this.getListeProduits()){
            System.out.println("    Produit: "+pa.getModele().getNomModele());
            System.out.println("    Quantite: "+pa.getQuantite());
        }
    }

    public double totaleAuPanier(String idModele){
        double totale = 0;
        for(ProduitAchetee pa : this.getListeProduits()){
            if(pa.getModele().getId().equals(idModele)){
                totale += pa.quantite;
            }
        }
        return totale;
    }

    public void addProduit(Connection cnx, Modele newProduit, double quantite) throws Exception {
        boolean closed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            closed = true;
        }

        double resteEnStock = newProduit.getResteEnStock(cnx);
        double resteApresAjout = resteEnStock - this.totaleAuPanier(newProduit.getId());
        String stockManquante = "Stock restant pour <b>"+newProduit.getNomModele()+"</b>: "+resteApresAjout;

        boolean exist = false;
        for(ProduitAchetee pa : this.getListeProduits()){
            if(pa.getModele().getId().equals(newProduit.getId())){
                quantite += pa.getQuantite();

                if(quantite > resteEnStock){
                    throw new Exception(stockManquante);
                }else{
                    pa.setQuantite(quantite);
                }
                exist = true;
                break;
            }
        }
        if(!exist){
            if(quantite > resteEnStock){
                throw new Exception(stockManquante);
            }else{
                this.getListeProduits().add(new ProduitAchetee(newProduit, quantite));
            }
        }

        if(closed){
            cnx.close();
        }
    }

    public void removeProduit(String idModele){
        List<ProduitAchetee> pas = this.getListeProduits();
        for(int i=0; i<pas.size(); i++){
            if(pas.get(i).getModele().getId().equals(idModele)){
                this.getListeProduits().remove(i);
                break;
            }
        }
    }


}
