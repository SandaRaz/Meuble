package entityView;

public class StatistiqueGenre {
    private String idModele;
    private String nomModele;
    private int idGenre;
    private String type;
    private double quantite;

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public StatistiqueGenre() {
    }

    public StatistiqueGenre(String idModele, String nomModele, int idGenre, String type, double quantite) {
        this.idModele = idModele;
        this.nomModele = nomModele;
        this.idGenre = idGenre;
        this.type = type;
        this.quantite = quantite;
    }

    public StatistiqueGenre(String nomModele, int idGenre, String type, double quantite) {
        this.nomModele = nomModele;
        this.idGenre = idGenre;
        this.type = type;
        this.quantite = quantite;
    }
}
