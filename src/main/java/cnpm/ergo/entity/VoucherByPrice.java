package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "voucher_by_price")
public class VoucherByPrice {

    @Id
    @Column(name = "voucherId")
    private int voucherId;

    @ManyToOne
    @JoinColumn(name = "voucherId", referencedColumnName = "voucherId", insertable = false, updatable = false)
    private Voucher voucher;  

    @Column(name = "lowerbound", nullable = false)
    private double lowerbound; 
}
