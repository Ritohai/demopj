package ra.dto.request;

import org.springframework.validation.Errors;
import ra.service.impl.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormRegisterDto {
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
    public FormRegisterDto() {
    }

    public FormRegisterDto(String username, String password, String email, String address, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void checkValidateRegister(Errors errors, UserService userService){

        // kiểm tra trường username có để trống hay không
        if(this.username.trim().equals("")) {
            errors.rejectValue("username", "username.empty");
        }else if(this.username.length()<6){
          errors.rejectValue("password", "password.regex");
        } else if(!userService.checkUsername(this.username)){
            errors.rejectValue("username","username.duplicate");
        }else  if (this.email == null || this.email.trim().isEmpty()) {
            errors.rejectValue("email", "email.empty");
        } else if (!isValidEmail(this.email)) {
            errors.rejectValue("email", "email.invalid");
        }else if(!userService.checkEmail(this.email)){
            errors.rejectValue("email", "email.duplicate");
        }else if (this.password == null ){
            errors.rejectValue("password", "password.empty");
        }else if (!isValidPassword(this.password)) {
            errors.rejectValue("password", "password.invalid");
        }
    }
}