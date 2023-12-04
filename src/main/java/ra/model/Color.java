package ra.model;

public class Color {
    private int id;
    private String color_name;

    private boolean status;

    public Color() {
    }

    public Color(int id, String color_name, boolean status) {
        this.id = id;
        this.color_name = color_name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
