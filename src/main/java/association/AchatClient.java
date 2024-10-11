package association;

import java.util.Date;

public class AchatClient {
    private int id;
    private String idClient;
    private String idModele;
    private double quantite;
    private Date dateAchat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) throws Exception {
        if(quantite < 0){
            throw new Exception("Quantite a une valeur negative");
        }
        this.quantite = quantite;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public AchatClient() {
    }

    public AchatClient(int id, String idClient, String idModele, double quantite, Date dateAchat) throws Exception {
        this.setId(id);
        this.setIdClient(idClient);
        this.setIdModele(idModele);
        this.setQuantite(quantite);
        this.setDateAchat(dateAchat);
    }

    public AchatClient(String idClient, String idModele, double quantite, Date dateAchat) throws Exception {
        this.setIdClient(idClient);
        this.setIdModele(idModele);
        this.setQuantite(quantite);
        this.setDateAchat(dateAchat);
    }
}
