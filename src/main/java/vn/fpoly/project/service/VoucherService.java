package vn.fpoly.project.service;

import org.springframework.stereotype.Service;
import vn.fpoly.project.model.voucher;
import vn.fpoly.project.repo.voucherRepo;

import java.util.List;

@Service
public class VoucherService {
    final private voucherRepo voucherRepo;

    public VoucherService(vn.fpoly.project.repo.voucherRepo voucherRepo) {
        this.voucherRepo = voucherRepo;
    }

    public List<voucher> getAll() {
        return voucherRepo.findAll();
    }

    public voucher getById(int id) {
        return voucherRepo.findById(id).get();
    }

    public List<voucher> getByName(String name) {
        return voucherRepo.findByName(name);
    }

    public void voucherAdd(voucher voucher) {
        voucherRepo.save(voucher);
    }

    public void voucherRemove(int id) {
        voucherRepo.deleteById(id);
    }


 }
