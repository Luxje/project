package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpoly.project.model.user;

public interface userRepo extends JpaRepository<user,Integer> {
}
