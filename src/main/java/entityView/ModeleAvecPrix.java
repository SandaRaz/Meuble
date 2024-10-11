/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entityView;

import entity.Modele;

/**
 *
 * @author Sanda
 */
public class ModeleAvecPrix extends Modele{
    private String id;
    private String nomModele;
    private double prixMatieres;
    private double totaleSalaire;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public double getPrixMatieres() {
        return prixMatieres;
    }

    public void setPrixMatieres(double prixMatieres) {
        this.prixMatieres = prixMatieres;
    }

    public double getTotaleSalaire() {
        return totaleSalaire;
    }

    public void setTotaleSalaire(double totaleSalaire) {
        this.totaleSalaire = totaleSalaire;
    }

    public ModeleAvecPrix() {
    }

    public ModeleAvecPrix(String idModele){
        this.id = idModele;
    }

    public ModeleAvecPrix(String id, String nomModele, double prixMatieres, double totaleSalaire) {
        this.id = id;
        this.nomModele = nomModele;
        this.prixMatieres = prixMatieres;
        this.totaleSalaire = totaleSalaire;
    }

    public ModeleAvecPrix(String nomModele, double prixMatieres, double totaleSalaire) {
        this.nomModele = nomModele;
        this.prixMatieres = prixMatieres;
        this.totaleSalaire = totaleSalaire;
    }
}
