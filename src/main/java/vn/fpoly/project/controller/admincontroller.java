package vn.fpoly.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.fpoly.project.Service.UserService;
import vn.fpoly.project.model.*;
import vn.fpoly.project.repo.*;


@Controller
@RequestMapping("/admin")
public class admincontroller {

    @Autowired
    productRepo repo;

    @Autowired
    UserService userService;

    @GetMapping("/page")
    public String page(Model model) {
        model.addAttribute("listProduct", repo.findAll());
        model.addAttribute("product", new products());
        return "adminpage";
    }

    @GetMapping("/product/edit/{id}")
    public String productedit(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", repo.findById(id).orElse(null));
        return "adminpage";
    }

    @PostMapping("/product/save")
    public String productsave(products product, Model model) {
        repo.save(product);
        return "redirect:/admin/page";
    }

    @GetMapping("/product/delete/{id}")
    public String productremove(@PathVariable("id") int id) {
        repo.deleteById(id);
        return "redirect:/admin/page";
    }

    @Autowired
    invoicesRepo repoinvoice;

    @GetMapping("/invoice")
    public String invoicepage(Model model) {
        model.addAttribute("listInvoice", repoinvoice.findAll());
        model.addAttribute("invoice", new invoices());
        return "admininvoice";
    }
    @Autowired
    voucherRepo vrepo;


    @Autowired
    staffRepo repostaff;

    @Autowired
    userRepo urepo;
    @GetMapping("/staff")
    public String staff(Model model){
       model.addAttribute("listStaff",repostaff.findAll());
       model.addAttribute("staff",new staff());
       return "adminstaff";

    }

    @PostMapping("/staff/save")
    public String savestaff(staff s,Model model){
        for (user u : urepo.findAll()){
            if(u.id == s.id && u.role.equals("STAFF")){
                 if(!u.phone.equals(s.phone)  || !u.address.equals(s.address) || u.age != s.age || u.gender != s.gender){
                     model.addAttribute("errorphone","thông tin khong hop le");
                     model.addAttribute("listStaff",repostaff.findAll());
                     model.addAttribute("staff",new staff());
                     return "adminstaff";
                 }else{
                     repostaff.save(s);
                 }
            }
        }
        return "redirect:/admin/staff";
    }

    @GetMapping("/staff/edit/{id}")
    public String editstaff(@PathVariable("id") int id,Model model){
        model.addAttribute("staff", repostaff.findById(id).orElse(null));
        model.addAttribute("listStaff",repostaff.findAll());
        return "adminstaff";
    }

    @GetMapping("/staff/delete/{id}")
    public String deletestaff(@PathVariable("id") int id){
        repostaff.deleteById(id);
        return "redirect:/admin/staff";
    }

    @GetMapping("/page/search")
    public String search(Model model,@RequestParam("keyword") String name){
        model.addAttribute("listProduct",repo.searchproduct(name));
        model.addAttribute("product",new products());
        return "adminpage";
    }

    @GetMapping("/khuyenmai")
    public String listvoucher(Model model){

        model.addAttribute("list",vrepo.findAll());
        model.addAttribute("voucher",new voucher());
       return "adminvoucher";
    }

    @PostMapping ("/savevoucher")
    public String save(voucher v){
        vrepo.save(v);
        return "redirect:/admin/khuyenmai";
    }
    @GetMapping("/voucherdelete")
    public String delete(@RequestParam("id") int id){
        vrepo.deleteById(id);
        return "redirect:/admin/khuyenmai";
    }
    @GetMapping("/voucherupdate")
    public String voucherupdate(Model model,@RequestParam("id") int id){
        model.addAttribute("list",vrepo.findAll());
        model.addAttribute("voucher",vrepo.findById(id).orElse(null));
        return "adminvoucher";
    }

    @GetMapping("/qltk")
    public String qltk(Model model) {
        model.addAttribute("lstTK", userService.findAll());
        model.addAttribute("user", new user());
        return "qltk";
    }

    @PostMapping("/qltk/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("phone") String phone,
                          @RequestParam("address") String address,
                          @RequestParam("age") int age,
                          @RequestParam("gender") boolean gender,
                          @RequestParam("role") String role,
                          @RequestParam("password") String password, Model model) {

        user u = new user(null, name, role, phone, address, age, gender, password);
        if (userService.add(u)) {
            model.addAttribute("message", "Thêm nhân viên thành công");
            return "redirect:/admin/qltk";
        }else {
            model.addAttribute("message", "Thêm nhân viên thất bại");
            return "qltk";
        }
    }

    @GetMapping("/qltk/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {

        if (userService.delete(id)) {
            model.addAttribute("message", "Xóa nhân viên thành công");
            return "redirect:/admin/qltk";
        }else {
            model.addAttribute("message", "Xóa nhân viên thất bại");
            return "redirect:/admin/qltk";
        }
    }

    @GetMapping("/qltk/detail/{id}")
    public String detailUser(@PathVariable("id") int id ,Model model) {
        user us = userService.findById(id);
        model.addAttribute("lstTK", userService.findAll());
        model.addAttribute("user", us);
        return "qltk";
    }


    @PostMapping("/qltk/update")
    public String updateUser(@ModelAttribute("user") user u, Model model) {
        if (userService.update(u)) {
            return "redirect:/admin/qltk";
        } else {
            model.addAttribute("message", "Cập nhật thất bại!");
            model.addAttribute("lstTK", userService.findAll());
            return "redirect:/admin/qltk";
        }
    }


    @GetMapping("/qltk/search")
    public String searchUser(@RequestParam("searchInput") String searchInput, Model model) {
        if (searchInput == null) {
            model.addAttribute("message", "Không tìm thấy nhân viên nào với tên:" + searchInput);
            return "qltk";
        }else {
            model.addAttribute("lstTK", userService.findUserByName(searchInput));
            return "qltk";
        }
    }

}
