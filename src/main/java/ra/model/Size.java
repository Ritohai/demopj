package ra.model;

public class Size {
    private int id;
    private String size_name;
    private boolean status = true;

    public Size() {
    }

    public Size(int id, String size_name, boolean status) {
        this.id = id;
        this.size_name = size_name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
