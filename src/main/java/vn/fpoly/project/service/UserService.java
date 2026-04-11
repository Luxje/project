package vn.fpoly.project.service;


import org.springframework.stereotype.Service;
import vn.fpoly.project.model.user;
import vn.fpoly.project.repo.userRepo;

import java.util.List;

@Service
public class UserService {
    final private userRepo repo;


    public UserService(userRepo repo) {
        this.repo = repo;
    }


    public user findById(int id) {
        return repo.findById(id);
    }

    public List<user> findAll() {
        return repo.findAll();
    }

    public boolean add(user u) {
        if (u == null || u.getPhone() == null) {
            return false;
        } else if (repo.existsByPhone(u.getPhone())) {
            return false;
        }
            repo.save(u);
            return true;
    }

    public boolean delete(int id) {
        if (!repo.existsById(id)) {
            return false;
        }
        else {
            repo.deleteById(id);
            return true;
        }
    }

    public boolean update(user u) {
        try {
            if (!repo.existsById(u.getId())) {
                return false;
            }
            else {
                repo.save(u);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public String validateRole(String phone) {
        user us = repo.findByPhone(phone);
        if (us == null) {
            return null;
        }
        return us.getRole();
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
            return false;
        }
        else {
            us.setPassword(newPassword);
            repo.save(us);
            return true;
        }
        }
        return false;
    }


    public List<user> findUserByName(String name) {
        return repo.findByNameContaining(name);
    }

}
