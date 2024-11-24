package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "voucher_by_product")
public class VoucherByProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "voucherId", referencedColumnName = "voucherId", nullable = false)
    private Voucher voucher;

    @Id
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Product product;
}
