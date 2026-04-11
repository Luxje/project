package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpoly.project.model.voucher;

import java.util.List;

public interface voucherRepo extends JpaRepository<voucher,Integer> {
    public List<voucher> findByName(String name);
}
