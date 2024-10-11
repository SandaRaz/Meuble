/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entityView;

import cnx.Connex;
import entity.Categorie;
import entity.Modele;
import entity.Style;
import entity.Volume;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanda
 */
public class ModeleComplet extends Modele{
    private String nomcategorie;
    private String nomstyle;
    private String nomvolume;
    private int echelle;
    protected double prix;
    protected double benefice;

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public String getNomModele() {
        return super.getNomModele();
    }

    public void setNomModele(String nomModele) {
        super.setNomModele(nomModele);
    }

    public int getIdCategorie() {
        return super.getIdCategorie();
    }

    public void setIdCategorie(int idCategorie) {
        super.setIdCategorie(idCategorie);
    }

    public String getNomcategorie() {
        return this.nomcategorie;
    }

    public void setNomcategorie(String nom) {
        this.nomcategorie = nom;
    }

    public int getIdStyle() {
        return super.getIdStyle();
    }

    public void setIdStyle(int idStyle) {
        super.setIdStyle(idStyle);
    }

    public String getNomstyle() {
        return nomstyle;
    }

    public void setNomstyle(String nomstyle) {
        this.nomstyle = nomstyle;
    }

    public int getIdVolume() {
        return super.getIdVolume();
    }

    public void setIdVolume(int idVolume) {
        super.setIdVolume(idVolume);
    }

    public String getNomvolume() {
        return this.nomvolume;
    }

    public void setNomvolume(String nomvolume) {
        this.nomvolume = nomvolume;
    }

    public int getEchelle() {
        return this.echelle;
    }

    public void setEchelle(int echelle) {
        this.echelle = echelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getBenefice() {
        return benefice;
    }

    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }

    public int getStatut(){
        return super.getStatut();
    }
    public void setStatut(int statut){
        super.setStatut(statut);
    }

    public ModeleComplet() {
    }

    public ModeleComplet(String id) {
        super(id);
    }

    public ModeleComplet(String id, String nomModele, int idCategorie, String nomcategorie, int idStyle, String nomstyle, int idVolume, String nomvolume, int echelle) {
        super(id,nomModele,idCategorie,idStyle,idVolume);

        this.nomcategorie = nomcategorie;
        this.nomstyle = nomstyle;
        this.nomvolume = nomvolume;
        this.echelle = echelle;
    }
    public ModeleComplet(String nomModele, int idCategorie, String nomcategorie, int idStyle, String nomstyle, int idVolume, String nomvolume, int echelle) {
        super(nomModele,idCategorie,idStyle,idVolume);

        this.nomcategorie = nomcategorie;
        this.nomstyle = nomstyle;
        this.nomvolume = nomvolume;
        this.echelle = echelle;
    }
    
    public ModeleComplet(String nomModele, Categorie categorie, Style style, Volume volume){
        super(nomModele, categorie.getId(), style.getId(), volume.getId());

        this.nomcategorie = categorie.getNom();
        this.nomstyle = style.getNomstyle();
        this.nomvolume = volume.getNomVolume();
        this.echelle = volume.getEchelle();
    }

    public ModeleComplet(String id, String nomModele, int idCategorie, String nomcategorie, int idStyle, String nomstyle, int idVolume, String nomvolume, int echelle, double prix) {
        super(id,nomModele,idCategorie,idStyle,idVolume);

        this.nomcategorie = nomcategorie;
        this.nomstyle = nomstyle;
        this.nomvolume = nomvolume;
        this.echelle = echelle;
        this.prix = prix;
    }

    public ModeleComplet(String id, String nomModele, int idCategorie, String nomcategorie, int idStyle, String nomstyle, int idVolume, String nomvolume, int echelle, double prix, double benefice) {
        super(id,nomModele,idCategorie,idStyle,idVolume);

        this.nomcategorie = nomcategorie;
        this.nomstyle = nomstyle;
        this.nomvolume = nomvolume;
        this.echelle = echelle;
        this.prix = prix;
        this.benefice = benefice;
    }


    public List<ModeleComplet> getAllByOrder(Connection cnx) throws Exception {
        List<ModeleComplet> modeleComplets = new ArrayList<>();

        boolean closed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            closed = true;
        }

        String sql = "SELECT id,nommodele,statut,idcategorie,nomcategorie,idstyle,nomstyle,idvolume,nomvolume,echelle,statut,prixdevente FROM DetailModele WHERE statut > 0 ORDER BY idcategorie,idstyle,idvolume";
        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            System.out.println("Query >>> "+pstmt.toString());

            ResultSet res = pstmt.executeQuery();

            ModeleComplet mc = null;
            while(res.next()){
                String id = res.getString("id");
                String nomModele = res.getString("nommodele");
                int idCategorie = res.getInt("idcategorie");
                String nomCategorie = res.getString("nomcategorie");
                int idStyle = res.getInt("idstyle");
                String nomStyle = res.getString("nomstyle");
                int idVolume = res.getInt("idvolume");
                String nomVolume = res.getString("nomvolume");
                int echelle = res.getInt("echelle");
                double prixVente = res.getDouble("prixdevente");

                mc = new ModeleComplet(id,nomModele,idCategorie,nomCategorie,idStyle,nomStyle,idVolume,nomVolume,echelle,prixVente);

                modeleComplets.add(mc);
            }
        }

        if(closed){
            cnx.close();
        }
        return modeleComplets;
    }
}
