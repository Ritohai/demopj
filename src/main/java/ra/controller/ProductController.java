package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.dto.request.FormProductDto;
import ra.model.Product;
import ra.service.impl.*;

import java.io.File;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ObjectService objectService;
    @Autowired
    private SizeService  sizeService;

    private final String pathUpload="C:\\Users\\ADMIN\\Desktop\\MD4_Project-main\\src\\main\\webapp\\WEB-INF\\image\\";
    @GetMapping("product")
    public ModelAndView adminProduct(Model model) {
        model.addAttribute("category",catalogService.findAll());
        model.addAttribute("products", productService.getFivedProducts());
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("objects", objectService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("colors", colorService.findAll());
        return new ModelAndView("admin/product");
    }

    @GetMapping("/pageProduct")
    public ModelAndView pageProduct(@RequestParam("id") int id, Model model) {
        model.addAttribute("category", catalogService.findAll());
        model.addAttribute("products", productService.getPageProducts(id));
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("objects", objectService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("colors", colorService.findAll());
        return new ModelAndView("admin/product");
    }

    @GetMapping("create")
    public ModelAndView adminCreateProduct(Model model) {
        model.addAttribute("catalog", catalogService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("objects", objectService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("colors", colorService.findAll());
        return new ModelAndView("admin/create","product",new Product());
    }

    @PostMapping("/add-product")
    public String createProduct( @ModelAttribute("product") FormProductDto formProductDto){
        File file = new File(pathUpload);
        if(!file.exists()) {
            file.mkdir();
        }
        String  urlImg = formProductDto.getImage_url().getOriginalFilename();
        try{
            FileCopyUtils.copy(formProductDto.getImage_url().getBytes(), new File(pathUpload + urlImg));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        Product p = new Product();
        p.setId(formProductDto.getId());
        p.setProduct_name(formProductDto.getProduct_name());
        p.setImage_url(urlImg);
        p.setDescription(formProductDto.getDescription());
        p.setStock(formProductDto.getStock());
        p.setImport_price(formProductDto.getImport_price());
        p.setExport_price(formProductDto.getExport_price());
        p.setCatalog(formProductDto.getCatalog());
        p.setSize(formProductDto.getSize());
        p.setBrand(formProductDto.getBrand());
        p.setColor(formProductDto.getColor());
        p.setUser_object(formProductDto.getUser_object());
        p.setStatus(formProductDto.isStatus());
        productService.save(p);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/product";
    }
    @GetMapping("edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id,Model model) {
        model.addAttribute("catalog", catalogService.findAll());
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("objects", objectService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("colors", colorService.findAll());
        return new ModelAndView("admin/edit","product",productService.findById(id));
    }
    @PostMapping("/edit_product")
    public String editProduct( @ModelAttribute("product") FormProductDto formProductDto){
        File file = new File(pathUpload);
        if(!file.exists()) {
            file.mkdir();
        }
        String  urlImg = formProductDto.getImage_url().getOriginalFilename();
        try{
            FileCopyUtils.copy(formProductDto.getImage_url().getBytes(), new File(pathUpload + urlImg));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        Product p = new Product();
        p.setId(formProductDto.getId());
        p.setProduct_name(formProductDto.getProduct_name());
        p.setImage_url(urlImg);
        p.setDescription(formProductDto.getDescription());
        p.setStock(formProductDto.getStock());
        p.setImport_price(formProductDto.getImport_price());
        p.setExport_price(formProductDto.getExport_price());
        p.setCatalog(formProductDto.getCatalog());
        p.setSize(formProductDto.getSize());
        p.setBrand(formProductDto.getBrand());
        p.setColor(formProductDto.getColor());
        p.setUser_object(formProductDto.getUser_object());
        p.setStatus(formProductDto.isStatus());
        productService.save(p);
        return "redirect:/product";
    }

    @GetMapping("inform/{id}")
    public ModelAndView informProduct(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("admin/product-inform");
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @GetMapping("/filter")
    public ModelAndView getSearchName(@RequestParam("min") Double min,@RequestParam("max") Double max, Model model) {
        model.addAttribute("catalog", catalogService.findAll());
        return new ModelAndView("shop", "products", productService.filterProduct(min,max));
    }

    @GetMapping("/objectSearch")
    public String modelAndView(@RequestParam("id") int id, Model model) {
        model.addAttribute("products",productService.getProductsByObject(id));
        model.addAttribute("catalog", catalogService.findAll());
        model.addAttribute("objects", objectService.findAll());
        return "shop";
    }

    @PostMapping("searchProduct")
    public ModelAndView searchProduct(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("category",catalogService.findAll());
        model.addAttribute("products", productService.searchProduct(keyword));
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("objects", objectService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("colors", colorService.findAll());
        return new ModelAndView("admin/product");
    }

    @GetMapping("/toggleProduct/{id}")
    public String getTogleProduct(@PathVariable("id") int id ,Model model){
        productService.toggleProStatus(id);
        return "redirect:/product";
    }
    @GetMapping("/product-detail/{id}")
    public String getProductInform(@PathVariable("id") int id, Model model) {
        model.addAttribute("product" ,productService.findById(id));
        model.addAttribute("catalog", catalogService.findAll());
        model.addAttribute("color", colorService.findAll());
        model.addAttribute("size",sizeService.findAll());
        model.addAttribute("object",objectService.findAll());
        model.addAttribute("brand",brandService.findAll());
        return "redirect:/product-detail";
    }


}
