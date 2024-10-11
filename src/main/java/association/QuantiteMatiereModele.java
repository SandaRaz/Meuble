package association;

public class QuantiteMatiereModele {
    private int id;
    private String idModele;
    private int idMatiere;
    private double quantite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public QuantiteMatiereModele(int id, String idModele, int idMatiere, double quantite) {
        this.id = id;
        this.idModele = idModele;
        this.idMatiere = idMatiere;
        this.quantite = quantite;
    }

    public QuantiteMatiereModele(String idModele, int idMatiere, double quantite) {
        this.idModele = idModele;
        this.idMatiere = idMatiere;
        this.quantite = quantite;
    }
}
