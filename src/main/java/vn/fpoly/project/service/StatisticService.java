package vn.fpoly.project.service;

import org.springframework.stereotype.Service;
import vn.fpoly.project.model.invoices;
import vn.fpoly.project.repo.invoicesRepo;

import java.util.List;

@Service
public class StatisticService {
    final private invoicesRepo invoicesRepo;

    public StatisticService(vn.fpoly.project.repo.invoicesRepo invoicesRepo) {
        this.invoicesRepo = invoicesRepo;
    }

    public List<invoices> getAll() {
        return invoicesRepo.findAll();
    }




    public Float caculateRevenue(List<invoices> invoices) {
        float sum = 0;
        for (invoices invoice : invoices) {
            sum += invoice.getPrice();
        }
        return sum;
    }
}

