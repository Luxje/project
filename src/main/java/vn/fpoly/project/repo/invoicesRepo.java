package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.fpoly.project.model.invoices;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface invoicesRepo extends JpaRepository<invoices,Integer> {
    List<invoices> findByDateStartingWith(String datePattern);

    List<invoices> findByDateBetween(String start, String end);


}


