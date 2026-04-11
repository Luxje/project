package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpoly.project.model.user;

import java.util.List;

public interface userRepo extends JpaRepository<user,Integer> {
    public user findByPhone(String phone);

    public user findById(int id);

    public List<user> findByNameContaining(String name);

    public boolean existsByPhone(String phone);

    public boolean existsById(int id);

}
