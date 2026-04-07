package vn.fpoly.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.fpoly.project.model.*;
import vn.fpoly.project.repo.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class admincontroller {

    final private productRepo repo;
    final private userRepo urepo;
    final private invoicesRepo repoinvoice;
    final private voucherRepo vrepo;

    public admincontroller(productRepo repo, invoicesRepo repoinvoice, voucherRepo vrepo, userRepo urepo) {
        this.repo = repo;
        this.repoinvoice = repoinvoice;
        this.vrepo = vrepo;
        this.urepo = urepo;
    }

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


    @GetMapping("/invoice")
    public String invoicepage(Model model) {
        model.addAttribute("listInvoice", repoinvoice.findAll());
        model.addAttribute("invoice", new invoices());
        return "admininvoice";
    }
    


    @Autowired
    staffRepo repostaff;

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
    @GetMapping("/user")
    public String userlist(Model model){
        model.addAttribute("acounts",urepo.findAll());
        model.addAttribute("account",new user());
        return "adminqluser";

    }
}
