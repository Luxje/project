package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpoly.project.model.invoice_item;

public interface invoiceitemsRepo extends JpaRepository<invoice_item,Integer> {
}
