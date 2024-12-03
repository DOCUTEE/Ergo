package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "product")
@NamedQuery(name = "product.findAll", query = "SELECT p FROM Product p")
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId;
    @Column(name = "name", columnDefinition = "NVARCHAR(200) NOT NULL")
    private String name;

    @Column(name = "descript", columnDefinition = "TEXT")
    private String descript;

    @Column(name = "isDelete", columnDefinition = "BIT")
    private boolean isDelete;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductType> productTypes;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductImage> productImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Wishlist> wishlists;

}