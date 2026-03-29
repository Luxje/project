package vn.fpoly.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.fpoly.project.service.UserService;


@Controller
@RequestMapping("/user/*")
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
                return "redirect:/staff/page";
            }
        }
        return "login";
    }
}
