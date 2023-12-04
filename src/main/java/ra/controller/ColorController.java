package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Brand;
import ra.model.Color;
import ra.service.impl.ColorService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class ColorController {
    //    private static final Gson GSON = new GsonBuilder().create();
    @Autowired
    private ColorService colorService;

    @GetMapping("/color")
    public ModelAndView adminBrand(Model model) {
        model.addAttribute("colors", colorService.findAll());
        return new ModelAndView("admin/color");
    }

    @PostMapping("/create-color")
    public String createColor(@ModelAttribute("colors") Color color, @RequestParam("color_name") String color_name) {
        System.out.println("a");
        color.setColor_name(color_name);
        colorService.save(color);
        return "redirect:/color";
    }

    @GetMapping("/editColor/{id}")
    public String editColor(@PathVariable("id") int id, Model model) {
        Color color = colorService.findById(id);
        model.addAttribute("colorEdit", color);
        return "admin/editColor";
    }

    @PostMapping("/editColor/{id}")
    public String editColor(@ModelAttribute("colorEdit") Color color) {
        System.out.println(color.getColor_name());
        System.out.println(color.getId());
        colorService.save(color);
        return "redirect:/color";
    }


    @GetMapping("/deleteColor/{id}")
    public String deleteColor(@PathVariable("id") int id) {
        colorService.delete(id);
        return "redirect:/color";
    }
}
