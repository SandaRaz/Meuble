package object;

public class Pagination {
    private int offset;
    private int limit;
    private int totaleLigne;
    private int nombrePage;
    private int numPageActuel;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotaleLigne() {
        return totaleLigne;
    }

    public void setTotaleLigne(int totaleLigne) {
        this.totaleLigne = totaleLigne;
    }

    public int getNombrePage() {
        return nombrePage;
    }

    public void setNombrePage(int nombrePage) {
        this.nombrePage = nombrePage;
    }

    public int getNumPageActuel() {
        return numPageActuel;
    }

    public void setNumPageActuel(int numPageActuel) {
        this.numPageActuel = numPageActuel;
    }

    public Pagination() {
    }

    public Pagination(int offset, int limit, int totaleLigne, int nombrePage, int numPageActuel) {
        this.offset = offset;
        this.limit = limit;
        this.totaleLigne = totaleLigne;
        this.nombrePage = nombrePage;
        this.numPageActuel = numPageActuel;
    }
}
