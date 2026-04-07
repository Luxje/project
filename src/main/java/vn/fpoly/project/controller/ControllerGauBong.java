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

    @GetMapping("/sanpham")
    public String sanpham() {
        return "sanpham";
    }

    @GetMapping("/changepass")
    public String changepass() {
        return "changepass"; // tên file doimatkhau.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin/qlsp")
    public String qlsp(Model model) {

        // Nếu sau này có dữ liệu thì truyền vào đây
        // model.addAttribute("products", list);

        return "admin/qlsp"; // admin.html
    }

    @GetMapping("/admin/qlkm")
    public String qlkm() {
        return "admin/qlkm";
    }

    @GetMapping("/admin/qlkh")
    public String customers() {
        return "qltk";
    }

    @GetMapping("/admin/qlnv")
    public String employees() {
        return "admin/qlnv";
    }

    @GetMapping("/admin/qlhd")
    public String orders() {
        return "admin/qlhd";
    }

    @GetMapping("/taikhoan")
    public String taikhoan() {
        return "taikhoan";
    }

    @GetMapping("/admin/tkdt")
    public String thongke() {
        return "admin/tkdt";
    }
}
