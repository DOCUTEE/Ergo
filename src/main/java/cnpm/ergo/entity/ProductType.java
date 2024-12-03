package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "product_type")
@NamedQuery(name = "ProductType.findAll", query = "SELECT p FROM ProductType p")

public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeId")
    private int typeId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Column(name = "color", columnDefinition = "NVARCHAR(50)")
    private String color;

    @Column(name = "length")
    private double length;

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "weight")
    private double weight;

    @Column(name = "material", columnDefinition = "NVARCHAR(100)")
    private String material;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToMany(mappedBy = "productTypes")
    private List<VoucherByProduct> voucher;

    @OneToMany(mappedBy = "productType")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "productType")
    private List<CartItem> cartItems;
    
    @Override
    public String toString() {
        return "ProductType [typeId=" + typeId + 
               ", color=" + color + 
               ", length=" + length + 
               ", width=" + width + 
               ", height=" + height + 
               ", weight=" + weight + 
               ", material=" + material + 
               ", price=" + price + 
               ", quantity=" + quantity + "]";
    }


}