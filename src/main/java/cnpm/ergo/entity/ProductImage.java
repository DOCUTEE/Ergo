package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product_image")
@NamedQuery(name = "ProductImage.findAll", query = "SELECT p FROM ProductImage p")
public class ProductImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productImageId")
    private int productImageId;

    @Column(name = "productImage", columnDefinition = "NVARCHAR(500) NOT NULL")
    private String productImage;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

}
