package vn.fpoly.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.fpoly.project.model.invoice_item;
import vn.fpoly.project.model.invoices;
import vn.fpoly.project.model.user;
import vn.fpoly.project.repo.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class SanphamvahoadonController {
    @Autowired
    productRepo repo;
    @Autowired
    voucherRepo voucher;
    @Autowired
     productRepo productrepo;
    @Autowired
    userRepo userRepo;
    @Autowired
    invoicesRepo invoicesrepo;
    @Autowired
    invoiceitemsRepo repoitemsinvoice;
  @PostMapping("/muasanpham")
    public String muasanpham(Model model, @RequestParam("id") Integer id){
      System.out.println(id);
          model.addAttribute("sp",repo.findById(id).orElse(null));
          model.addAttribute("vouchers",voucher.findAll());
          return "muahang";
  }
  @PostMapping("/xacnhan")
    public String xacnhan(Model model,@RequestParam int id,
                             @RequestParam String customerName,
                             @RequestParam String phone,
                             @RequestParam String address,
                             @RequestParam String role,
                             @RequestParam Float total,
                             @RequestParam Boolean gender,
                             @RequestParam int voucherId,
                             @RequestParam int quantity
  ){
      System.out.println(total);
      System.out.println("Voucher ID đang insert: " + voucherId);
       int iduser = 0;
      user user = new user(customerName,role,phone,address,18,gender,"123");
      userRepo.save(user);
      for (user u : userRepo.findAll()){
          if(u.phone == phone)
              iduser = u.id;
      }
     int voucherid = voucherId;
       LocalDate datenotformat = LocalDate.now();
       String date = datenotformat.toString();
      invoices invoices = new invoices("hoa don "+date,total,voucherid,2,date,iduser);
      invoicesrepo.save(invoices);
      int idinvoices = 0;
      for (invoices i : invoicesrepo.findAll()){
          if(i.customerid == iduser)
              idinvoices = i.id;
      }
      Float price = quantity*productrepo.findById(id).orElse(null).price;
      invoice_item iitems = new invoice_item(idinvoices,2,quantity,price);
      repoitemsinvoice.save(iitems);
      model.addAttribute("invoice",invoicesrepo.findById(idinvoices).orElse(null));
      return "hoadon";
  }
}
