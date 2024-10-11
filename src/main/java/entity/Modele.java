/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import cnx.Connex;
import dao.GenericDao;
import entityView.*;
import exception.StockManquante;
import object.SearchParam;
import simpleController.CtrlAnnotation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sanda
 */
public class Modele {
    private String id;
    private String nomModele;
    private int idCategorie;
    private int idStyle;
    private int idVolume;

    private int statut;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nom) {
        this.nomModele = nom;
    } 

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }

    public int getIdVolume() {
        return idVolume;
    }

    public void setIdVolume(int idVolume) {
        this.idVolume = idVolume;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Modele() {
    }

    public Modele(String id) {
        this.setId(id);
    }

    public Modele(String id, String nomModele) {
        this.setId(id);
        this.setNomModele(nomModele);
    }

    public Modele(String id, String nomModele, int idCategorie, int idStyle, int idVolume) {
        this.setId(id);
        this.setNomModele(nomModele);
        this.setIdCategorie(idCategorie);
        this.setIdStyle(idStyle);
        this.setIdVolume(idVolume);
    }

    public Modele(String nomModele, int idCategorie, int idStyle, int idVolume) {
        this.setNomModele(nomModele);
        this.setIdCategorie(idCategorie);
        this.setIdStyle(idStyle);
        this.setIdVolume(idVolume);
    }

    public Modele(String id, String nomModele, int idCategorie, int idStyle, int idVolume, int statut) {
        this.setId(id);
        this.setNomModele(nomModele);
        this.setIdCategorie(idCategorie);
        this.setIdStyle(idStyle);
        this.setIdVolume(idVolume);
        this.setStatut(statut);
    }

    public Modele(String nomModele, int idCategorie, int idStyle, int idVolume, int statut) {
        this.setNomModele(nomModele);
        this.setIdCategorie(idCategorie);
        this.setIdStyle(idStyle);
        this.setIdVolume(idVolume);
        this.setStatut(statut);
    }

    public boolean exist(Connection cnx) throws Exception{
        boolean exist = false;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM Modele WHERE idCategorie='"+this.getIdCategorie()+"' AND idStyle='"+this.getIdStyle()+"' AND idVolume='"+this.getIdVolume()+"'";
        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql)){
                if(res.next()){
                    exist = true;
                }
            }
        
        if(isclosed){
            cnx.close();
        }
        return exist;
    }
    
    public List<QuantiteMatiereDetail> getListeMatiereDetaillee(Connection cnx) throws Exception{
        List<QuantiteMatiereDetail> matieres = new ArrayList<>();
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT * FROM QuantiteMatiereDetail WHERE idModele = ?";
        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            pstmt.setString(1, this.getId());
            
            try(ResultSet res = pstmt.executeQuery()){
                while(res.next()){
                    QuantiteMatiereDetail qmm = new QuantiteMatiereDetail(res.getInt("id"), 
                    res.getString("idmodele"),res.getInt("idmatiere"),
                    res.getString("nommatiere"),res.getDouble("prix"),
                    res.getDouble("quantite"));
                    matieres.add(qmm);
                }
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return matieres;
    }
    
    public int fabriquer(Connection cnx, double quantite) throws StockManquante, Exception{
        int ligneInseree = 0;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        cnx.setAutoCommit(false);
        
        try{
            List<QuantiteMatiereDetail> mesMatieres = this.getListeMatiereDetaillee(cnx);
            for(QuantiteMatiereDetail matiereDetail : mesMatieres){
                double stockRestante = matiereDetail.getResteEnStock(cnx);
                double stockRequis = matiereDetail.getQuantite() * quantite;
                if(stockRequis > stockRestante){
                    throw new StockManquante("Stock de matiere premiere <b>"+matiereDetail.getNommatiere()+"</b> manquante. ", stockRequis, stockRestante);
                }
            }

            for(QuantiteMatiereDetail matiereDetail : mesMatieres){
                double stockRequis = matiereDetail.getQuantite() * quantite;
                MvtStockMatiere stockage = new MvtStockMatiere(matiereDetail.getIdMatiere(), 0, stockRequis, new Date());
                
                GenericDao.Save(cnx, stockage);
            }

            double prixRevient = this.getPrixDeRevient(cnx);
            double prixVente = this.getPrixDeVente(cnx);
            StockProduit fabrication = new StockProduit(this.getId(),quantite, 0, prixRevient, prixVente,new Date());
            ligneInseree = GenericDao.Save(cnx, fabrication);
            
            cnx.commit();
        }catch(Exception e){
            cnx.rollback();
            e.printStackTrace();
            throw e;
        }
        
        if(isclosed){
            cnx.close();
        }
        return ligneInseree;
    }
    
    public double getPrixDeRevient(Connection cnx) throws Exception{
        double result = 0;
        
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        String sql = "SELECT (prixmatieres + totalesalaire) as prixRevient FROM Modeleavecprix WHERE id = '"+this.getId()+"'";
        Statement stmt = cnx.createStatement();
        System.out.println("SQL >>> "+sql);
        try(ResultSet res = stmt.executeQuery(sql);){
            while(res.next()){
                result = res.getDouble("prixRevient");
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        return result;
    }
    
    public double getPrixDeVente(Connection cnx) throws Exception{
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        double prixDeRevient = this.getPrixDeRevient(cnx);
        double marge = Marge.getPourcentage(cnx, prixDeRevient);
        
        double prixDeVente = prixDeRevient + ((marge*prixDeRevient)/100);
        
        if(isclosed){
            cnx.close();
        }
        return prixDeVente;
    }

    public double getResteEnStock(Connection cnx) throws SQLException {
        double reste = 0;

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT reste FROM StockEtat WHERE idmodele = ?";
        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            pstmt.setString(1, this.getId());

            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                reste = res.getDouble("reste");
            }
        }

        if(isclosed){
            cnx.close();
        }
        return reste;
    }

    public double getValeurStock(Connection cnx) throws SQLException {
        double valeur = 0;

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT valeur FROM StockEtat WHERE idmodele = ?";
        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            pstmt.setString(1, this.getId());

            ResultSet res = pstmt.executeQuery();
            if(res.next()){
                valeur = res.getDouble("valeur");
            }
        }

        if(isclosed){
            cnx.close();
        }
        return valeur;
    }
    
    public double getBenefice(Connection cnx) throws Exception{
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        double benefice = this.getPrixDeVente(cnx) - this.getPrixDeRevient(cnx);
        
        if(isclosed){
            cnx.close();
        }
        return benefice;
    }
    
    public List<ModeleAvecPrix> rechercheParBenefice(Connection cnx, double min, double max) throws Exception{
        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }
        
        List<ModeleAvecPrix> modeles = new ArrayList<>();
        
        List<Object> objects = GenericDao.FindAll(cnx, new ModeleAvecPrix());
        for(Object o : objects){
            ModeleAvecPrix map = (ModeleAvecPrix) o;
            double benefice = map.getBenefice(cnx);
            if(benefice >= min && benefice <= max){
                modeles.add(map);
            }
        }
        
        if(isclosed){
            cnx.close();
        }
        
        return modeles;
    }

    public List<StatistiqueGenre> getAllStatistique(Connection cnx) throws Exception{
        List<StatistiqueGenre> result = new ArrayList<StatistiqueGenre>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT * FROM StatistiqueGenreDetail";
        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql);){
            Modele modele = null;
            while(res.next()){
                String idModele = res.getString("idModele");
                String nomModele = res.getString("nomModele");
                int idGenre = res.getInt("idGenre");
                String type = res.getString("type");
                double quantite = res.getDouble("quantite");
                StatistiqueGenre stG = new StatistiqueGenre(idModele,nomModele,idGenre,type,quantite);
                result.add(stG);
            }
        }

        if(isclosed){
            cnx.close();
        }
        return result;
    }

    public List<StatistiqueGenre> getModeleStatistique(Connection cnx) throws Exception{
        List<StatistiqueGenre> result = new ArrayList<StatistiqueGenre>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT * FROM StatistiqueGenreDetail WHERE idModele = '"+this.getId()+"'";
        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql);){
            Modele modele = null;
            while(res.next()){
                String idModele = res.getString("idModele");
                String nomModele = res.getString("nomModele");
                int idGenre = res.getInt("idGenre");
                String type = res.getString("type");
                double quantite = res.getDouble("quantite");
                StatistiqueGenre stG = new StatistiqueGenre(idModele,nomModele,idGenre,type,quantite);
                result.add(stG);
            }
        }

        if(isclosed){
            cnx.close();
        }
        return result;
    }

    public List<StockProduitDetail> getListMouvementGlobale(Connection cnx) throws Exception{
        List<StockProduitDetail> result = new ArrayList<>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT * FROM StockProduitDetail";
        try(Statement stmt = cnx.createStatement()){
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String idModele = res.getString("idModele");
                String nomModele = res.getString("nomModele");
                double entrer = res.getDouble("entrer");
                double sortie = res.getDouble("sortie");
                double prixRevient = res.getDouble("prixrevient");
                double prixVente = res.getDouble("prixvente");
                Date dateMvt = res.getDate("dateMvt");

                StockProduitDetail stpd = new StockProduitDetail(id,idModele,entrer,sortie,prixRevient,prixVente,dateMvt,nomModele);
                result.add(stpd);
            }
        }

        if(isclosed){
            cnx.close();
        }
        return result;
    }

    public List<StockProduitDetail> getListMouvementGlobale(Connection cnx, int offset, int limit) throws Exception{
        List<StockProduitDetail> result = new ArrayList<>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT * FROM StockProduitDetail ORDER BY datemvt DESC OFFSET ? LIMIT ?";
        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            pstmt.setInt(1, offset);
            pstmt.setInt(2, limit);

            ResultSet res = pstmt.executeQuery();
            while(res.next()){
                int id = res.getInt("id");
                String idModele = res.getString("idModele");
                String nomModele = res.getString("nomModele");
                double entrer = res.getDouble("entrer");
                double sortie = res.getDouble("sortie");
                double prixRevient = res.getDouble("prixrevient");
                double prixVente = res.getDouble("prixvente");
                Date dateMvt = res.getDate("dateMvt");

                StockProduitDetail stpd = new StockProduitDetail(id,idModele,entrer,sortie,prixRevient,prixVente,dateMvt,nomModele);
                result.add(stpd);
            }
        }

        if(isclosed){
            cnx.close();
        }
        return result;
    }

    public List<StockProduitDetail> getListMouvement(Connection cnx) throws Exception{
        List<StockProduitDetail> result = new ArrayList<>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT * FROM StockProduitDetail WHERE idModele = ?";
        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            pstmt.setString(1, this.getId());
            ResultSet res = pstmt.executeQuery();

            Modele modele = null;
            while(res.next()){
                int id = res.getInt("id");
                String idm = res.getString("idModele");
                String nomModele = res.getString("nomModele");
                double entrer = res.getDouble("entrer");
                double sortie = res.getDouble("sortie");
                double prixRevient = res.getDouble("prixrevient");
                double prixVente = res.getDouble("prixvente");
                Date dateMvt = res.getDate("dateMvt");
                StockProduitDetail stpd = new StockProduitDetail(id,idm,entrer,sortie,prixRevient,prixVente,dateMvt,nomModele);
                result.add(stpd);
            }
        }

        if(isclosed){
            cnx.close();
        }
        return result;
    }

    public List<StockProduitDetail> getListMouvement(Connection cnx, int offset, int limit) throws Exception{
        List<StockProduitDetail> result = new ArrayList<>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT * FROM StockProduitDetail WHERE idModele = ? ORDER BY datemvt DESC OFFSET ? LIMIT ?";
        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            pstmt.setString(1, this.getId());
            pstmt.setInt(2, offset);
            pstmt.setInt(3, limit);

            System.out.println("Query >>> "+pstmt.toString());

            ResultSet res = pstmt.executeQuery();

            Modele modele = null;
            while(res.next()){
                int id = res.getInt("id");
                String idm = res.getString("idModele");
                String nomModele = res.getString("nomModele");
                double entrer = res.getDouble("entrer");
                double sortie = res.getDouble("sortie");
                double prixRevient = res.getDouble("prixrevient");
                double prixVente = res.getDouble("prixvente");
                Date dateMvt = res.getDate("dateMvt");
                StockProduitDetail stpd = new StockProduitDetail(id,idm,entrer,sortie,prixRevient,prixVente,dateMvt,nomModele);
                result.add(stpd);
            }
        }

        if(isclosed){
            cnx.close();
        }
        return result;
    }

    public int getNombreMouvement(Connection cnx) throws Exception{
        int ligne = 0;

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT count(*) as ligne FROM StockProduitDetail WHERE idModele ='"+this.getId()+"'";
        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql)){
            if(res.next()){
                ligne = res.getInt("ligne");
            }
        }

        if(isclosed){
            cnx.close();
        }
        return ligne;
    }

    public int getNombreMouvementGlobale(Connection cnx) throws Exception{
        int ligne = 0;

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT count(*) as ligne FROM StockProduitDetail";
        Statement stmt = cnx.createStatement();
        try(ResultSet res = stmt.executeQuery(sql)){
            if(res.next()){
                ligne = res.getInt("ligne");
            }
        }

        if(isclosed){
            cnx.close();
        }
        return ligne;
    }


    // --------------------- RECHERCHE -----------------------
    public List<ModeleComplet> rechercheByPrix(Connection cnx, double prixMin,double prixMax) throws Exception{
        List<ModeleComplet> modeles = new ArrayList<>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT id,nommodele,idcategorie,nomcategorie,idstyle,nomstyle,idvolume,nomvolume,echelle,prixmatieres FROM DetailModele WHERE prixMatieres >= ? AND prixMatieres <= ? AND statut > 0";

        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            pstmt.setDouble(1, prixMin);
            pstmt.setDouble(2, prixMax);

            System.out.println("Query >>> "+pstmt.toString());

            ResultSet res = pstmt.executeQuery();
            ModeleComplet modele = null;
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
                double prixMatiere = res.getDouble("prixmatieres");

                modele = new ModeleComplet(id,nomModele,idCategorie,nomCategorie,idStyle,nomStyle,idVolume,nomVolume,echelle,prixMatiere);
                modeles.add(modele);
            }
        }

        if(isclosed){
            cnx.close();
        }
        return modeles;
    }

    public List<ModeleComplet> rechercheByBenefice(Connection cnx, double beneficeMin,double beneficeMax) throws Exception{
        List<ModeleComplet> modeles = new ArrayList<>();

        boolean isclosed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            isclosed = true;
        }

        String sql = "SELECT id,nommodele,idcategorie,nomcategorie,idstyle,nomstyle,idvolume,nomvolume,echelle,benefice FROM DetailModele WHERE benefice >= ? AND benefice <= ? AND statut > 0";

        try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
            pstmt.setDouble(1, beneficeMin);
            pstmt.setDouble(2, beneficeMax);

            System.out.println("Query >>> "+pstmt.toString());

            ResultSet res = pstmt.executeQuery();
            ModeleComplet modele = null;
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
                double benefice = res.getDouble("benefice");

                modele = new ModeleComplet(id,nomModele,idCategorie,nomCategorie,idStyle,nomStyle,idVolume,nomVolume,echelle,benefice);
                System.out.println("NomModele >>> "+modele.getNomModele());
                modeles.add(modele);
            }
        }

        if(isclosed){
            cnx.close();
        }
        return modeles;
    }

    public List<ModeleComplet> rechercheAvance(Connection cnx, SearchParam searchParam) throws SQLException {
        List<ModeleComplet> modeles = new ArrayList<>();

        boolean closed = false;
        if(cnx.isClosed()){
            cnx = Connex.PsqlConnect();
            closed = true;
        }
        String sqlQuery = "SELECT * FROM DetailModele WHERE ";
        String queryParam = "";

        if(searchParam.isCheckCategorie()){
            if(!queryParam.isBlank()){
                queryParam += " AND ";
            }
            queryParam += "idCategorie = '"+searchParam.getIdCategorie()+"'";
        }
        if(searchParam.isCheckStyle()){
            if(!queryParam.isBlank()){
                queryParam += " AND ";
            }
            queryParam += "idStyle = '"+searchParam.getIdStyle()+"'";
        }
        if(searchParam.isCheckVolume()){
            if(!queryParam.isBlank()){
                queryParam += " AND ";
            }
            queryParam += "echelle "+searchParam.getVolumeComparaison()+" '"+searchParam.getEchelleVolume()+"'";
        }
        if(searchParam.isCheckPrix()){
            if(!queryParam.isBlank()){
                queryParam += " AND ";
            }
            queryParam += "prixDeVente "+searchParam.getPrixComparaison()+" '"+searchParam.getPrix()+"'";
        }
        if(searchParam.isCheckBenefice()){
            if(!queryParam.isBlank()){
                queryParam += " AND ";
            }
            queryParam += "benefice "+searchParam.getBeneficeComparaison()+" '"+searchParam.getBenefice()+"'";
        }

        if(!queryParam.isBlank()){
            sqlQuery += queryParam + " AND statut > 0";
            System.out.println("Seach+ Query >>> "+sqlQuery);

            Statement stmt = cnx.createStatement();
            try(ResultSet res = stmt.executeQuery(sqlQuery)){
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
                    double prix = res.getDouble("prixdevente");
                    double benefice = res.getDouble("benefice");

                    mc = new ModeleComplet(id,nomModele,idCategorie,nomCategorie,idStyle,nomStyle,idVolume,nomVolume,echelle,prix,benefice);
                    modeles.add(mc);
                }
            }
        }else{
            System.out.println("Seach+ Query is Blank");
        }

        System.out.println("Result length: "+modeles.size());
        return modeles;
    }

    // -------------------------------------------------------
}
