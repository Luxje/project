package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpoly.project.model.voucher;

public interface voucherRepo extends JpaRepository<voucher,Integer> {
}
