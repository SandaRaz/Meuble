/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entityView;

import entity.MvtStockMatiere;
import java.util.Date;

/**
 *
 * @author Sanda
 */
public class MvtStockMatiereDetail extends MvtStockMatiere{
    private int id;
    private int idMatiere;
    private String nommatiere;
    private double entrer;
    private double sortie;
    private Date dateMvt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getIdMatiere() {
        return idMatiere;
    }

    @Override
    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNommatiere() {
        return nommatiere;
    }

    public void setNommatiere(String nommatiere) {
        this.nommatiere = nommatiere;
    }

    @Override
    public double getEntrer() {
        return entrer;
    }

    @Override
    public void setEntrer(double entrer) {
        this.entrer = entrer;
    }

    @Override
    public double getSortie() {
        return sortie;
    }

    @Override
    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    @Override
    public Date getDateMvt() {
        return dateMvt;
    }

    @Override
    public void setDateMvt(Date dateMvt) {
        this.dateMvt = dateMvt;
    }

    public MvtStockMatiereDetail() {
    }
    
    

    public MvtStockMatiereDetail(int id, int idMatiere, String nommatiere, double entrer, double sortie, Date dateMvt) {
        this.id = id;
        this.idMatiere = idMatiere;
        this.nommatiere = nommatiere;
        this.entrer = entrer;
        this.sortie = sortie;
        this.dateMvt = dateMvt;
    }
    
    public MvtStockMatiereDetail(int idMatiere, String nommatiere, double entrer, double sortie, Date dateMvt) {
        this.idMatiere = idMatiere;
        this.nommatiere = nommatiere;
        this.entrer = entrer;
        this.sortie = sortie;
        this.dateMvt = dateMvt;
    }
}
