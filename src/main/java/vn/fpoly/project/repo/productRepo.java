package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpoly.project.model.products;

public interface productRepo extends JpaRepository<products,Integer> {
}
