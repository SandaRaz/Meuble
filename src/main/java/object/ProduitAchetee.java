package object;

import entity.Modele;

public class ProduitAchetee {
    Modele modele;
    double quantite = 0;

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public ProduitAchetee() {
    }

    public ProduitAchetee(Modele modele, double quantite) {
        this.modele = modele;
        this.quantite = quantite;
    }
}
