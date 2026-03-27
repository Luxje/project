package vn.fpoly.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class invoices {
    public invoices(String name, Float price, Integer voucher, Integer staffid, String date, Integer customerid) {
        this.name = name;
        this.price = price;
        this.voucher = voucher;
        this.staffid = staffid;
        this.date = date;
        this.customerid = customerid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "invoice_name")
    public String name;

    @Column(name = "total_price")
    public Float price;

    @Column(name = "voucher_id")
    public Integer voucher;

    @Column(name = "staff_id")
    public Integer staffid;


    @Column(name = "invoice_date")
    public String date;

    @Column(name = "customer_id")
    public Integer customerid;
}
