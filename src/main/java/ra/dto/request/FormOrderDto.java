package ra.dto.request;

public class FormOrderDto {
    private int id;
    private String  receiver;
    private String zipcode;
    private String phone;
    private String address;
    private String ortherInfo;

    public FormOrderDto() {
    }

    public FormOrderDto(String receiver, String zipcode, String phone, String address, String ortherInfo) {
        this.receiver = receiver;
        this.zipcode = zipcode;
        this.phone = phone;
        this.address = address;
        this.ortherInfo = ortherInfo;
    }

    public String getReceiver() {
        return receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    public String getOrtherInfo() {
        return ortherInfo;
    }

    public void setOrtherInfo(String ortherInfo) {
        this.ortherInfo = ortherInfo;
    }
}
