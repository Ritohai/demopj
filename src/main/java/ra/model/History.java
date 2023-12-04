package ra.model;

import java.util.Date;

public class History {
    private int id;
    private String byUser ;
    private String product_name;
    private int quantity;
    private Double price;
    private Date date;
    private String receiver;
    private String phone;
    private String address;
    private Boolean status;

    public History() {
    }

    public History(int id, String product_name, int quantity, Double price, Date date, String receiver, String phone, String address, Boolean status) {
        this.id = id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public History(int id, String byUser, String product_name, int quantity, Double price, Date date, String receiver, String phone, String address, Boolean status) {
        this.id = id;
        this.byUser = byUser;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getByUser() {
        return byUser;
    }

    public void setByUser(String byUser) {
        this.byUser = byUser;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
