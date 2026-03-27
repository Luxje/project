package vn.fpoly.project;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerGauBong {

    @GetMapping("/")
    public String home() {
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
