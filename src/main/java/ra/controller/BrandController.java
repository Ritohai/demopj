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
import ra.service.impl.BrandService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class BrandController {
    private static final Gson GSON = new GsonBuilder().create();

    @Autowired
    private BrandService brandService;
    @GetMapping("/brand")
    public ModelAndView adminBrand(Model model){
        model.addAttribute("brands", brandService.findAll());
        return new ModelAndView("admin/brand");
    }
    @PostMapping("/create-brand")
    public String createCategory(@ModelAttribute("brand") Brand brand, @RequestParam("brand_name") String brand_name) {
        System.out.println("a");
        brand.setBrand_name(brand_name);
        brandService.save(brand);
        return "redirect:/brand";
    }

    @GetMapping("/editBrand/{id}")
    public String editBrand(@PathVariable("id") int id, Model model) {
        Brand brandEdit = brandService.findById(id);
        model.addAttribute("brandEdit", brandEdit);
        return "admin/editBrand";
    }

    @PostMapping("/editBrand/{id}")
    public String editBrand(@ModelAttribute("brandEdit") Brand brand) {
        brandService.save(brand);
        return "redirect:/brand";
    }


    @GetMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable("id") int id){
        brandService.delete(id);
        return "redirect:/brand";
    }
}
