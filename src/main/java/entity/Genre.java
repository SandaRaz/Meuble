package entity;

public class Genre {
    private int id;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Genre() {
    }

    public Genre(int id, String type) {
        this.id = id;
        this.type = type;
    }
    public Genre(int id) {
        this.id = id;
    }

    public Genre(String type) {
        this.type = type;
    }
}
