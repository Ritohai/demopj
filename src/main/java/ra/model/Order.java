package ra.model;

import java.util.Date;

public class Order {
    private int id;
    private int user_id;
    private Date export_Date;
    private boolean status;

    public Order() {
    }

    public Order(int id, int user_id, Date export_Date, boolean status) {
        this.id = id;
        this.user_id = user_id;
        this.export_Date = export_Date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public Date getExport_Date() {
        return export_Date;
    }

    public void setExport_Date(Date export_Date) {
        this.export_Date = export_Date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
