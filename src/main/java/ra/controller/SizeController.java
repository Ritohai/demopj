package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Brand;
import ra.model.Catalog;
import ra.model.Size;
import ra.service.impl.SizeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class SizeController {
    private static final Gson GSON = new GsonBuilder().create();
    @Autowired
    private SizeService sizeService;
    @GetMapping("/size")
    public ModelAndView adminSize(Model model){
        model.addAttribute("sizes", sizeService.findAll());
        return new ModelAndView("admin/size");
    }
    @PostMapping("/create-size")
    public String createSize(@ModelAttribute("size_name") Size size, @RequestParam("size_name") String size_name) {
        size.setSize_name(size_name);
        sizeService.save(size);
        return "redirect:/size";
    }

    @GetMapping("/editSize/{id}")
    public String editSize(@PathVariable("id") int id, Model model) {
        Size sizeEdit = sizeService.findById(id);
        model.addAttribute("sizeEdit", sizeEdit);
        return "admin/editSize";
    }

    @PostMapping("/editSize/{id}")
    public String editBrand(@ModelAttribute("sizeEdit") Size size) {
        sizeService.save(size);
        return "redirect:/size";
    }


    @GetMapping("/deleteSize/{id}")
    public String deleteCatalog(@PathVariable("id") int id){
        sizeService.delete(id);
        return "redirect:/size";
    }

}
