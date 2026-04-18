package vn.fpoly.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.fpoly.project.model.*;
import vn.fpoly.project.repo.*;
import vn.fpoly.project.service.StatisticService;
import vn.fpoly.project.service.UserService;
import vn.fpoly.project.service.VoucherService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class admincontroller {

    final private productRepo repo;
    final private userRepo urepo;
    final private invoicesRepo repoinvoice;
    final private voucherRepo vrepo;
    final private VoucherService voucherService;
    final private UserService userService;
    final private StatisticService statisticService;
    @Autowired
    private vn.fpoly.project.repo.invoicesRepo invoicesRepo;

    public admincontroller(productRepo repo, invoicesRepo repoinvoice, voucherRepo vrepo, userRepo urepo, VoucherService voucherService, UserService userService, StatisticService statisticService) {
        this.repo = repo;
        this.repoinvoice = repoinvoice;
        this.vrepo = vrepo;
        this.urepo = urepo;
        this.voucherService = voucherService;
        this.userService = userService;
        this.statisticService = statisticService;
    }

    @GetMapping("/qlsp")
    public String product(Model model) {
        model.addAttribute("listProduct", repo.findAll());
        model.addAttribute("product", new products());
        return "admin/qlsp";
    }

    @GetMapping("/product/edit/{id}")
    public String productedit(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", repo.findById(id).orElse(null));
        return "admin/qlsp";

    }

    @PostMapping("/product/save")
    public String productsave(@RequestParam("name") String name,
                              @RequestParam("price") float price,
                              @RequestParam("quantity") int quantity,
                              @RequestParam("status") boolean status, Model model) {
        repo.save(new products(null, name, price, quantity, status));
        return "redirect:/admin/qlsp";
    }

    @GetMapping("/product/delete/{id}")
    public String productremove(@PathVariable("id") int id) {
        repo.deleteById(id);
        return "redirect:/admin/qlsp";
    }

    @GetMapping("/product/search")
    public String search(Model model,@RequestParam("keyword") String name){
        model.addAttribute("listProduct",repo.searchproduct(name));
        model.addAttribute("product",new products());
        return "admin/qlsp";
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
       return "admin/qlnv";

    }

    @PostMapping("/staff/save")
    public String savestaff(@ModelAttribute("staff") staff s,Model model){
        model.addAttribute("staff", new staff());
        model.addAttribute("listStaff",repostaff.findAll());
        repostaff.save(s);
        return "redirect:/admin/staff";
        }


    @GetMapping("/staff/edit/{id}")
    public String editstaff(@PathVariable("id") int id,Model model){
        model.addAttribute("staff", repostaff.findById(id).orElse(null));
        model.addAttribute("listStaff",repostaff.findAll());
        return "/admin/qlnv";
    }

    @GetMapping("/staff/delete/{id}")
    public String deletestaff(@PathVariable("id") int id){
        repostaff.deleteById(id);
        return "redirect:/admin/staff";
    }


    @GetMapping("/account")
    public String account(Model model) {
        model.addAttribute("listAccount", urepo.findAll());
        return "/admin/qltk";
    }


    @GetMapping("staff/detail/{id}")
    public String detailstaff(@PathVariable("id") int id ,Model model) {
        model.addAttribute("staff", repostaff.findById(id));
        model.addAttribute("listStaff",repostaff.findAll());
        return "/admin/qlnv";
    }



    @GetMapping("/user/search")
    public String search(@RequestParam("search") String searchInput, Model model) {
        List<user> lstTK = userService.findUserByName(searchInput);
        if (lstTK == null){
            model.addAttribute("message", "Không tìm thấy nhân viên với tên: " + searchInput);
            return "/admin/qltk";
        }else {
            model.addAttribute("lstTK", lstTK);
            return "/admin/qltk";
        }
    }


    @GetMapping("/qltk")
    public String qltk(Model model) {
        model.addAttribute("lstTK", userService.findAll());
        model.addAttribute("user", new user());
        return "/admin/qltk";
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
            return "/admin/qltk";
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
        return "/admin/qltk";
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
            return "/admin/qltk";
        }else {
            model.addAttribute("lstTK", userService.findUserByName(searchInput));
            return "/admin/qltk";
        }
    }


    @GetMapping("/qldt")
    public String statistical(Model model) {
        List<invoices> lstInvoice = statisticService.getAll();
        List<invoices> lstInvoiceToday = statisticService.getAllToDay();
        model.addAttribute("todayRevenue", statisticService.caculateRevenuePrice(lstInvoiceToday));
        model.addAttribute("totalRevenue", statisticService.caculateRevenuePrice(lstInvoice));
        model.addAttribute("totalOrders", lstInvoice.size());

        return "/admin/tkdt";
    }

    @GetMapping("/qldt/sort")
    public String sortByDay(@RequestParam("startDate") String startDate,
                            @RequestParam("endDate") String endDate, Model model) {

        model.addAttribute("revenueList", statisticService.getAllInTime(startDate, endDate));
        List<invoices> lstInvoice = statisticService.getAll();
        List<invoices> lstInvoiceToday = statisticService.getAllToDay();
        model.addAttribute("todayRevenue", statisticService.caculateRevenuePrice(lstInvoiceToday));
        model.addAttribute("totalRevenue", statisticService.caculateRevenuePrice(lstInvoice));
        model.addAttribute("totalOrders", lstInvoice.size());

        return "/admin/tkdt";

    }


    @GetMapping("/qlkm")
    public String voucher(Model model) {
        model.addAttribute("voucher", new voucher());
        model.addAttribute("lstVoucher", voucherService.getAll());
        return "/admin/qlkm";
    }

    @PostMapping("/qlkm/add")
    public String addVoucher(@ModelAttribute("voucher") voucher voucher, Model model) {
        voucherService.voucherAdd(voucher);
        model.addAttribute("lstVoucher", voucherService.getAll());
        return "redirect:/admin/qlkm";
    }

    @GetMapping("/qlkm/remove/{id}")
    public String removeVoucher(@PathVariable("id") int id, Model model) {
        voucherService.voucherRemove(id);
        model.addAttribute("lstVoucher", voucherService.getAll());
        return "redirect:/admin/qlkm";
    }

    @GetMapping("/qlkm/detail/{id}")
    public String detailVoucher(@PathVariable("id") int id, Model model) {
        model.addAttribute("voucher", voucherService.getById(id));
        model.addAttribute("lstVoucher", voucherService.getAll());
        return "/admin/qlkm";
    }


    @PostMapping("/qlkm/edit")
    public String updateVoucher(@ModelAttribute("voucher") voucher voucher, Model model) {

        voucherService.voucherAdd(voucher);
        return "redirect:/admin/qlkm";
    }

    @GetMapping("/qlhd")
    public String qlhd(Model model) {
        model.addAttribute("invoices", invoicesRepo.findAll());
        return "/admin/qlhd";
    }


}
