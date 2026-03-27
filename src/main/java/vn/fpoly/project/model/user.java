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
@Table(name = "users")
public class user {
    public user(String name, String role, String phone, String address, Integer age, Boolean gender, String password) {
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name_user")
    public String name;

    @Column(name = "user_role")
    public String role;

    @Column(name = "user_phone")
    public String phone;

    @Column(name = "user_address")
    public String address;

    @Column(name = "user_age")
    public Integer age;

    @Column(name = "user_gender")
    public Boolean gender;

    @Column(name = "user_password")
    public String password;
}
