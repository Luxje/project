package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpoly.project.model.staff;

@Repository
public interface staffRepo extends JpaRepository<staff,Integer> {
}
