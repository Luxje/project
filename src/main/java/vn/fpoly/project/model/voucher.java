package vn.fpoly.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public String startdate;

    @Column(name = "voucher_end")
    public String enddate;

    @Column(name = "voucher_value")
    public Float value;
}
