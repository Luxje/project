package vn.fpoly.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.fpoly.project.model.invoice_item;
import vn.fpoly.project.model.invoices;
import vn.fpoly.project.model.products;
import vn.fpoly.project.model.voucher;
import vn.fpoly.project.repo.*;
import vn.fpoly.project.model.user;

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
    

    @GetMapping("/staff")
    public String staff(Model model){
        List list = new ArrayList();
        for (user u : urepo.findAll()){
            if(u.role.equals("STAFF") || u.role.equals("staff")){
                list.add(u);
            }
        }
        model.addAttribute("listStaff",list);
        model.addAttribute("staff",new user());
        return "adminstaff";
    }

    @PostMapping("/staff/save")
    public String savestaff(user u){
        urepo.save(u);
        return "redirect:/admin/staff";

    }

    @GetMapping("/staff/edit/{id}")
    public String editstaff(@PathVariable("id") int id,Model model){
        model.addAttribute("staff", urepo.findById(id).orElse(null));
        return "adminstaff";
    }
    @GetMapping("/staff/delete/{id}")
    public String deletestaff(@PathVariable("id") int id){
        urepo.deleteById(id);
        return "redirect:/admin/staff";
    }

}
