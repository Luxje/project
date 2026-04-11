package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpoly.project.model.invoices;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface invoicesRepo extends JpaRepository<invoices,Integer> {
    public List<invoices> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
