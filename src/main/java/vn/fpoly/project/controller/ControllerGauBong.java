package vn.fpoly.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.fpoly.project.repo.productRepo;

@Controller
public class ControllerGauBong {

    @Autowired
    productRepo repo;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("object",repo.findAll());
        return "index";
    }


    @GetMapping("/giohang")
    public String giohang() {
        return "giohang";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
}
