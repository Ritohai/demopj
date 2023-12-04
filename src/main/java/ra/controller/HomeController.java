package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.dto.request.FormLoginDto;
import ra.dto.request.FormOrderDto;
import ra.dto.request.FormRegisterDto;
import ra.model.User;
import ra.service.impl.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ObjectService objectService;


    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @GetMapping("/admin")
    public String getAdmin(HttpSession session) {
        User userlogin = (User) session.getAttribute("userlogin");
        int role = userlogin.getRole();
        if(role ==1 ){
            return "admin/index";
        }
     return "index";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login", "login_form", new FormLoginDto());
    }

    @PostMapping("/handle-login")
    public String handleLogin(HttpSession session, @ModelAttribute("login_form") FormLoginDto formLoginDto, BindingResult errors) {
        // checkk validate
        formLoginDto.checkValidate(errors,userService);
        // kiểm tra bindingresult có nhận lỗi nào không
        if(errors.hasErrors()){
            return "login";
        }
        User user = userService.login(formLoginDto);
        if(user.getRole()==2){
            session.setAttribute("userlogin",user);
            return "index";
        }else {
            return "admin/index";
        }

    }


    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.removeAttribute("userlogin");
        session.removeAttribute("carts");
        return "redirect:/";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register", "register_form", new FormRegisterDto());
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("register_form") FormRegisterDto formRegisterDto, BindingResult errors,Model model) {
        formRegisterDto.checkValidateRegister(errors, userService);
        if (errors.hasErrors()) {
            return "register";
        }
        userService.save(formRegisterDto);
        return "redirect:/login";
    }

    @GetMapping("/shop")
    public ModelAndView shop(Model model) {
        model.addAttribute("products",productService.findAll());
        model.addAttribute("catalog", catalogService.findAll());
        model.addAttribute("objects", objectService.findAll());
        return new ModelAndView("shop");
    }

    @GetMapping("/shopping-cart")
    public ModelAndView shoppingCart(HttpSession session) {

        return new ModelAndView("shopping-cart");
    }

    @GetMapping("/check-out")
    public ModelAndView checkout() {
        return new ModelAndView("check-out","order_form", new FormOrderDto());
    }

    @GetMapping("/blog")
    public ModelAndView blog() {
        return new ModelAndView("blog");
    }

    @GetMapping("/blog-details")
    public ModelAndView blogDetails() {
        return new ModelAndView("blog-details");
    }
    @GetMapping("/product-detail")
    public String productDetail() {
        return "product-detail";
    }

    @GetMapping("/contact")
    public ModelAndView contact() {
        return new ModelAndView("contact");
    }


    @GetMapping("/history")
    public ModelAndView history(HttpSession session) {
        User userlogin = (User) session.getAttribute("userlogin");
        return new ModelAndView("history","orders",historyService.getOrderByUser(userlogin.getId()));
    }

    @PostMapping("/search")
    public ModelAndView getSearchName(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("catalog", catalogService.findAll());
        return new ModelAndView("shop", "products", productService.searchProduct(keyword));
    }



}
