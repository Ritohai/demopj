package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Brand;
import ra.model.Object;
import ra.service.impl.CatalogService;
import ra.service.impl.ObjectService;
import ra.service.impl.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class ObjectController {
    private static final Gson GSON = new GsonBuilder().create();
    @Autowired
    private ObjectService objectService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CatalogService catalogService;
    @GetMapping("/object")
    public ModelAndView adminBrand(Model model){
        model.addAttribute("objects", objectService.findAll());
        return new ModelAndView("admin/object");
    }
    @PostMapping("/create-object")
    public String createCategory(@ModelAttribute("object") Object object, @RequestParam("object_name") String object_name) {
        System.out.println("a");
        object.setObject_name(object_name);
        objectService.save(object);
        return "redirect:/object";
    }

    @GetMapping("/editObject/{id}")
    public String editBrand(@PathVariable("id") int id, Model model) {
        Object objectEdit = objectService.findById(id);
        model.addAttribute("objectEdit", objectEdit);
        return "admin/editObject";
    }

    @PostMapping("/editObject/{id}")
    public String editObject(@ModelAttribute("objectEdit") Object object) {
        objectService.save(object);
        return "redirect:/object";
    }


    @GetMapping("/deleteObject/{id}")
    public String deleteObject(@PathVariable("id") int id){
        objectService.delete(id);
        return "redirect:/object";
    }

}
