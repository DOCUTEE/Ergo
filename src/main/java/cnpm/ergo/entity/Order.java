package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;

    @Column(name = "orderDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "status", columnDefinition = "NVARCHAR(30)")
    private String status;

    @Column(name = "cityOfProvince", columnDefinition = "NVARCHAR(50)")
    private String cityOfProvince;

    @Column(name = "district", columnDefinition = "NVARCHAR(50)")
    private String district;

    @Column(name = "ward", columnDefinition = "NVARCHAR(50)")
    private String ward;

    @Column(name = "streetNumber", columnDefinition = "NVARCHAR(50)")
    private String streetNumber;

    @Column(name = "phone", length = 10, nullable = false)
    private String phone;

    @Column(name = "totalCost", nullable = false)
    private double totalCost;

    @Column(name = "discount", nullable = false)
    private double discount;

    @Column(name = "actualCost", nullable = false)
    private double actualCost;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "userId", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "voucherId", referencedColumnName = "voucherId")
    private Voucher voucher;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems; 
}
