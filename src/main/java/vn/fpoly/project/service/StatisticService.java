package vn.fpoly.project.service;

import org.springframework.stereotype.Service;
import vn.fpoly.project.model.invoices;
import vn.fpoly.project.repo.invoiceitemsRepo;
import vn.fpoly.project.repo.invoicesRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public List<invoices> getAllToDay() {
        LocalDate today = LocalDate.now();
        String dateStr = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return invoicesRepo.findByDateStartingWith(dateStr);
    }


    public List<invoices> getAllInTime(String start, String end) {
        String startStr = start + " 00:00:00";
        String endStr = end + " 23:59:59";
        return invoicesRepo.findByDateBetween(startStr, endStr);
    }


    public Float caculateRevenuePrice(List<invoices> invoices) {
        float sum = 0;
        for (invoices invoice : invoices) {
            sum += invoice.getPrice();
        }
        return sum;
    }
}

