package entityView;

import cnx.Connex;
import entity.Modele;
import entity.StockProduit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockProduitDetail extends StockProduit {
    private String nomModele;
    private String couleur;

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur() {
        if (this.getEntrer() > this.getSortie()){
            this.couleur = "palegreen";
        }
        else {
            this.couleur = "#FF584D";
        }
    }

    public StockProduitDetail() {
        super();
    }

    public StockProduitDetail(int id, String idModele, double entrer, double sortie, double prixRevient, double prixVente, Date dateMvt, String nomModele) {
        super(id, idModele, entrer, sortie, prixRevient, prixVente, dateMvt);
        this.nomModele = nomModele;
        this.setCouleur();
    }

    public StockProduitDetail(String idModele, double entrer, double sortie, double prixRevient, double prixVente, Date dateMvt, String nomModele) {
        super(idModele, entrer, sortie, prixRevient, prixVente, dateMvt);
        this.nomModele = nomModele;
        this.setCouleur();
    }
}
