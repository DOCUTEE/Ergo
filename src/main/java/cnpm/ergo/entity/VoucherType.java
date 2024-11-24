package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "voucher_type")
public class VoucherType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeId")
    private int typeId;

    @Column(name = "typeName", columnDefinition = "NVARCHAR(30)", nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "voucherType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voucher> vouchers;
}
