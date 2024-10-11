package association;

import java.util.Date;

public class HistoriqueEmbauche {
    private int id;
    private String idEmploye;
    private int idPoste;
    private double tauxHoraire;
    private Date dateDebut;

    public HistoriqueEmbauche() {
    }

    public HistoriqueEmbauche(int id, String idEmploye, int idPoste, double tauxHoraire, Date dateDebut) {
        this.id = id;
        this.idEmploye = idEmploye;
        this.idPoste = idPoste;
        this.tauxHoraire = tauxHoraire;
        this.dateDebut = dateDebut;
    }

    public HistoriqueEmbauche(String idEmploye, int idPoste, double tauxHoraire, Date dateDebut) {
        this.idEmploye = idEmploye;
        this.idPoste = idPoste;
        this.tauxHoraire = tauxHoraire;
        this.dateDebut = dateDebut;
    }

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

    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
}
