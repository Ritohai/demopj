package ra.model;

public class Object {
    private int id;
    private String object_name;
    private boolean status = true;

    public Object() {
    }

    public Object(int id, String object_name, boolean status) {
        this.id = id;
        this.object_name = object_name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
