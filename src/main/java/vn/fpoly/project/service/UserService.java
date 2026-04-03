package vn.fpoly.project.service;


import org.springframework.stereotype.Service;
import vn.fpoly.project.model.user;
import vn.fpoly.project.repo.userRepo;

@Service
public class UserService {
    final private userRepo repo;


    public UserService(userRepo repo) {
        this.repo = repo;
    }

    public String validateRole(String phone) {
        user us = repo.findByPhone(phone);
        if (us == null) {
            return null;
        }
        else if (us.getRole().equals("ADMIN")) {
            return "ADMIN";
        }
        else if (us.getRole().equals("USER")) {
            return "USER";
        }else if (us.getRole().equals("CUSTOMER")) {
            return "CUSTOMER";
        }
        return null;
    }

    public boolean validateLogin(String phone, String password) {
        user us = repo.findByPhone(phone);
        if (us == null) {
            return false;
        }
        if (us.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public boolean passwordChange(String phone, String newPassword, String confirmPassword) {
        if (newPassword.equals(confirmPassword)) {
        user us = repo.findByPhone(phone);
        if (us == null) {
            System.out.println("doi mat khau that bai");
            return false;
        }
        else {
            us.setPassword(newPassword);
            repo.save(us);
            System.out.println("doi mat khau thanhc ong");
            return true;
        }
        }
        return false;
    }


}
