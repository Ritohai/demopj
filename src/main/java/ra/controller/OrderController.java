package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.dto.request.FormOrderDto;
import ra.model.*;
import ra.service.impl.BillingService;
import ra.service.impl.HistoryService;
import ra.service.impl.OrderService;
import ra.service.impl.ProductService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController {
@Autowired
private BillingService billingService;
@Autowired
private OrderService orderService;
@Autowired
private HistoryService historyService;
@Autowired
private ProductService productService;

    @PostMapping("/create_order")
    public String createOrder(HttpSession session, @ModelAttribute("order_form") FormOrderDto formOrderDto){
        User userlogin = (User) session.getAttribute("userlogin");
        List<Cart> carts = (List<Cart>) session.getAttribute("carts");

        Billing billing = new Billing();
        billing.setUser_id(userlogin.getId());
        billing.setReceiver(formOrderDto.getReceiver());
        billing.setZipcode(formOrderDto.getZipcode());
        billing.setAddress(formOrderDto.getAddress());
        billing.setPhone(formOrderDto.getPhone());
        billing.setOrtherInfo(formOrderDto.getOrtherInfo());
        billingService.save(billing);

        Order order = new Order();
        order.setUser_id(userlogin.getId());
        orderService.insertOrder(order,carts);

        for (Cart cart : carts) {
            Product product = productService.findById(cart.getProduct().getId());
            product.setStock(product.getStock() - cart.getQuantity());
            productService.save(product);
        }
        session.removeAttribute("carts");
        session.removeAttribute("total");
    return "redirect:/";
    }

    @GetMapping("/order")
    public String getOrder(Model model) {
        model.addAttribute("orders",historyService.findAll());
        return "admin/order";
    }

    @GetMapping("/confirm/{id}")
    public String lock(@PathVariable("id") int id) {
        orderService.toggleOrder(id);
        return "redirect:/order";
    }

}
