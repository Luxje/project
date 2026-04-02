package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpoly.project.model.invoices;

@Repository
public interface invoicesRepo extends JpaRepository<invoices,Integer> {
}
