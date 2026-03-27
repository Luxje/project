package vn.fpoly.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_items")
public class invoice_item {
    public invoice_item(Integer idBill, Integer productId, Integer quantity, Float price) {
        this.idBill = idBill;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "invoice_id")
    public Integer idBill;

    @Column(name = "product_id")
    public Integer productId;

    @Column(name = "quantity")
    public Integer quantity;

    @Column(name = "price")
    public Float price;
}
