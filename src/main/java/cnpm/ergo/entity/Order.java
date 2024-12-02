package cnpm.ergo.entity;

import cnpm.ergo.DAO.implement.UserDAOImpl;
import cnpm.ergo.configs.JPAConfig;
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;


    public static void main(String[] args) {
        // create Order object
        Order order = new Order();
        // set orderDate
        order.setOrderDate(new Date(System.currentTimeMillis()));
        // set status
        order.setStatus("Đã đặt hàng");
        // set cityOfProvince
        order.setCityOfProvince("Hà Nội");
        // set district
        order.setDistrict("Cầu Giấy");
        // set ward
        order.setWard("Nghĩa Đô");
        // set streetNumber
        order.setStreetNumber("Số 1, Đại Cồ Việt");
        // set phone
        order.setPhone("0123456789");
        // set totalCost
        order.setTotalCost(1000.0);
        // set discount
        order.setDiscount(100.0);
        // set actualCost
        order.setActualCost(900.0);
        // set customer
        User customer = new UserDAOImpl().getUserById(1);
        order.setCustomer(customer);
        // insert order to database
        EntityManager em = JPAConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }
}
