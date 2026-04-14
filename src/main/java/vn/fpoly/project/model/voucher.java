package vn.fpoly.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voucher")
public class voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "voucher_name")
    public String name;

    @Column(name = "voucher_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String startdate;

    @Column(name = "voucher_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String enddate;

    @Column(name = "voucher_value")
    public Float value;
}
