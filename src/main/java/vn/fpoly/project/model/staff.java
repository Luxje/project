package vn.fpoly.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staff")
public class staff {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "staff_phone")
    public String phone;

    @Column(name = "staff_address")
    public String address;

    @Column(name = "staff_age")
    public int age;

    @Column(name = "staff_gender")
    public Boolean gender;

    @Column(name = "staff_name")
    public String name;


}
