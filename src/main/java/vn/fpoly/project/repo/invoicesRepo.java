package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpoly.project.model.invoices;
public interface invoicesRepo extends JpaRepository<invoices,Integer> {
}
