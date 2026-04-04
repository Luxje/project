package vn.fpoly.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.fpoly.project.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String handlelogin(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        if (userService.validateLogin(phone, password)) {
            if (userService.validateRole(phone).equals("CUSTOMER")) {
                return "redirect:/";
            }else if (userService.validateRole(phone).equals("ADMIN")) {
                return "redirect:/admin/page";
            }else if (userService.validateRole(phone).equals("STAFF")) {
                return "redirect:/admin/page";
            }
        }
        return "login";
    }

    @GetMapping("/passwordchange")
    public String passwordchange() {
        return "passwordchange";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

//    @PostMapping("/register")
//    public String handleregister(@RequestParam("phone") String phone, @RequestParam("password") String password, Model model) {
//
//    }



    @GetMapping("/passwordchange")
    public String passwordchange() {
        return "changepass";
    }

    @PostMapping("/passwordchange")
    public String handlepasswordchange(@RequestParam("phone") String phone,
                                       @RequestParam("password") String password,
                                       @RequestParam("confirmPassword") String confirmPassword,
                                       Model model) {
            if (userService.passwordChange(phone, password, confirmPassword)) {
                model.addAttribute("message", "Đổi mật khẩu thành công");
                return "changepass";
            } else {
                model.addAttribute("message", "Đổi mật khẩu thất bại");
                return "changepass";
            }
    }

}
