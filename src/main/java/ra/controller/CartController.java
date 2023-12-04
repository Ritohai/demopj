package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Cart;
import ra.model.Product;
import ra.model.User;
import ra.service.impl.CartService;
import ra.service.impl.ProductService;
import ra.service.impl.UserService;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @RequestMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, HttpSession session, Model model) {
        User userLogin = (User) session.getAttribute("userlogin");

        if (userLogin == null) {
            model.addAttribute("login", "You need to be logged in to purchase.");
            return "login";
        }

        Product product = productService.findById(id);
        if (!product.isStatus()) {
            model.addAttribute("error", "The product has not been updated. Please wait later");
            return "index";
        }

        Cart cart = cartService.findByProductId(id);
        if (cart == null) {
            cart = new Cart();
            cart.setId(cartService.newId());
            cart.setProduct(product);
            cart.setQuantity(1);
            cart.setTotalPrice(product.getExport_price() * cart.getQuantity());
        } else {
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotalPrice(product.getExport_price() * cart.getQuantity());
        }

        cartService.save(cart);
        session.setAttribute("carts", cartService.findAll());
        double total = 0;
        for (Cart c:cartService.findAll()) {
            total += c.getTotalPrice();
        }
        session.setAttribute("total", total);
        return "redirect:/shop";
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable int id,HttpSession session) {
        List<Cart> carts = (List<Cart>) session.getAttribute("carts");
        if (carts != null) {
            Iterator<Cart> iterator = carts.iterator();
            while (iterator.hasNext()) {
                Cart cart = iterator.next();
                if (cart.getId() == id) {
                    iterator.remove();
                    break;
                }
            }
        }
        session.setAttribute("carts", carts);
        cartService.delete(id);
        return "redirect:/shopping-cart";
    }


    @PostMapping("/update/{id}")
    public  String handleUpdate(HttpSession session,@PathVariable("id") int id, @RequestParam("quantity") int quantity){
        Cart cart= cartService.findById(id);
        cart.setQuantity(quantity);
        cart.setTotalPrice(cart.getProduct().getExport_price()*cart.getQuantity());
        cartService.save(cart);
        // lưu vào session
        session.setAttribute("cart",cartService.findAll());
        double total = 0;
        for (Cart c:cartService.findAll()) {
            total += c.getTotalPrice();
        }
        session.setAttribute("total", total);
        return "redirect:/shopping-cart";
    }
}
