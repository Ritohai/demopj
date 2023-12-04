package ra.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class FormProductDto {
    private int id;
    private String product_name;
    private String description;
    private MultipartFile   image_url;
    private int stock;
    private int catalog;
    private int size;
    private int brand;
    private int color;
    private int user_object;
    private double import_price;
    private double export_price;
    private Date import_date;
    private boolean status;

    public FormProductDto() {
    }

    public FormProductDto(int id, String product_name, String description, MultipartFile image_url, int stock, int catalog, int size, int brand, int color, int user_object, double import_price, double export_price, Date import_date, boolean status) {
        this.id = id;
        this.product_name = product_name;
        this.description = description;
        this.image_url = image_url;
        this.stock = stock;
        this.catalog = catalog;
        this.size = size;
        this.brand = brand;
        this.color = color;
        this.user_object = user_object;
        this.import_price = import_price;
        this.export_price = export_price;
        this.import_date = import_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage_url() {
        return image_url;
    }

    public void setImage_url(MultipartFile image_url) {
        this.image_url = image_url;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCatalog() {
        return catalog;
    }

    public void setCatalog(int catalog) {
        this.catalog = catalog;
    }

    public double getImport_price() {
        return import_price;
    }

    public void setImport_price(double import_price) {
        this.import_price = import_price;
    }

    public double getExport_price() {
        return export_price;
    }

    public void setExport_price(double export_price) {
        this.export_price = export_price;
    }

    public Date getImport_date() {
        return import_date;
    }

    public void setImport_date(Date import_date) {
        this.import_date = import_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getUser_object() {
        return user_object;
    }

    public void setUser_object(int user_object) {
        this.user_object = user_object;
    }
}
