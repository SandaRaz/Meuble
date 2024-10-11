package object;

public class SearchParam {
    private boolean checkCategorie;
    private int idCategorie;

    private boolean checkStyle;
    private int idStyle;

    private boolean checkVolume;
    private String volumeComparaison;
    private int echelleVolume;

    private boolean checkPrix;
    private String prixComparaison;
    private double prix;

    private boolean checkBenefice;
    private String beneficeComparaison;
    private double benefice;

    public boolean isCheckCategorie() {
        return checkCategorie;
    }
    public void setCheckCategorie(String checked) {
        this.checkCategorie = checked != null;
    }
    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(String idCategorie) {
        if(idCategorie == null){
            this.idCategorie = 0;
        }else{
            this.idCategorie = Integer.parseInt(idCategorie);
        }
    }

    public boolean isCheckStyle() {
        return checkStyle;
    }
    public void setCheckStyle(String checked) {
        this.checkStyle = checked != null;
    }
    public int getIdStyle() {
        return idStyle;
    }
    public void setIdStyle(String idStyle) {
        if(idStyle == null){
            this.idStyle = 0;
        }else{
            this.idStyle = Integer.parseInt(idStyle);
        }
    }

    public boolean isCheckVolume() {
        return checkVolume;
    }
    public void setCheckVolume(String checked) {
        this.checkVolume = checked != null;
    }
    public String getVolumeComparaison() {
        return volumeComparaison;
    }
    public void setVolumeComparaison(String volumeComparaison) {
        this.volumeComparaison = volumeComparaison;
    }
    public int getEchelleVolume() {
        return echelleVolume;
    }
    public void setEchelleVolume(String echelleVolume) {
        if(echelleVolume == null){
            this.echelleVolume = 0;
        }else{
            this.echelleVolume = Integer.parseInt(echelleVolume);
        }
    }

    public boolean isCheckPrix() {
        return checkPrix;
    }
    public void setCheckPrix(String checked) {
        this.checkPrix = checked != null;
    }
    public String getPrixComparaison() {
        return prixComparaison;
    }
    public void setPrixComparaison(String prixComparaison) {
        this.prixComparaison = prixComparaison;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(String prix) {
        if(prix == null || prix.isBlank()){
            this.prix = 0;
        }else{
            this.prix = Double.parseDouble(prix);
        }
    }

    public boolean isCheckBenefice() {
        return checkBenefice;
    }
    public void setCheckBenefice(String checked) {
        this.checkBenefice = checked != null;
    }
    public String getBeneficeComparaison() {
        return beneficeComparaison;
    }
    public void setBeneficeComparaison(String beneficeComparaison) {
        this.beneficeComparaison = beneficeComparaison;
    }
    public double getBenefice() {
        return benefice;
    }
    public void setBenefice(String benefice) {
        if(benefice == null || benefice.isBlank()){
            this.benefice = 0;
        }else{
            this.benefice = Double.parseDouble(benefice);
        }
    }

    public SearchParam() {
    }
}
