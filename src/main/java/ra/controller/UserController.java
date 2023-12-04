package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.service.impl.UserService;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public ModelAndView getUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return new ModelAndView("admin/user");
    }

    @GetMapping("/lock/{id}")
    public String lock(@PathVariable("id") int id) {
        userService.toggleUserStatus(id);
        return "redirect:/user";
    }
}
