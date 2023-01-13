package com.sha.springbootmicro.Controller;

import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class İndex {

    private ProductService productservice;

    public İndex(ProductService productservice) {
        this.productservice = productservice;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products",productservice.find_all_products());
        return "index";
    }
}
