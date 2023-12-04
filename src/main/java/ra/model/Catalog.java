package ra.model;

public class Catalog {
    private int id;
    private String catalog_name;

    public Catalog() {
    }

    public Catalog(int id, String catalog_name) {
        this.id = id;
        this.catalog_name = catalog_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatalog_name() {
        return catalog_name;
    }

    public void setCatalog_name(String catalog_name) {
        this.catalog_name = catalog_name;
    }
}
