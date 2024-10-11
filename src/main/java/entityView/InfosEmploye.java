package entityView;

public class InfosEmploye {
    private int id;
    private String idEmploye;
    private String nom;
    private int idPoste;
    private String titre;
    private double tauxHoraire;
    private String dateEmbauche;
    private String dateDebutPoste;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getDateDebutPoste() {
        return dateDebutPoste;
    }

    public void setDateDebutPoste(String dateDebutPoste) {
        this.dateDebutPoste = dateDebutPoste;
    }

    public InfosEmploye() {
    }

    public InfosEmploye(int id, String idEmploye, String nom, int idPoste, String titre, double tauxHoraire, String dateEmbauche, String dateDebutPoste) {
        this.id = id;
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.idPoste = idPoste;
        this.titre = titre;
        this.tauxHoraire = tauxHoraire;
        this.dateEmbauche = dateEmbauche;
        this.dateDebutPoste = dateDebutPoste;
    }

    public InfosEmploye(String idEmploye, String nom, int idPoste, String titre, double tauxHoraire, String dateEmbauche, String dateDebutPoste) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.idPoste = idPoste;
        this.titre = titre;
        this.tauxHoraire = tauxHoraire;
        this.dateEmbauche = dateEmbauche;
        this.dateDebutPoste = dateDebutPoste;
    }
}
