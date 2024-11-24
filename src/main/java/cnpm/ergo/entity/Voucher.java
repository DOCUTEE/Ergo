package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucherId")
    private int voucherId;

    @Column(name = "dateStart", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateStart; 

    @Column(name = "dateEnd", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateEnd; 

    @Column(name = "code", nullable = false, length = 20)
    private String code; 

    @Column(name = "discount", nullable = false)
    private double discount; 

    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "typeId", nullable = false)
    private VoucherType voucherType;

    @OneToMany(mappedBy = "voucher")
    private List<Order> orders;

    @OneToMany(mappedBy = "voucher")
    private List<VoucherByProduct> voucherByProducts; 

    @OneToMany(mappedBy = "voucher")
    private List<VoucherByPrice> voucherByPrices; 

//    @OneToMany(mappedBy = "voucher")
//    private List<MarketingCampaign> marketingCampaigns; 
}
