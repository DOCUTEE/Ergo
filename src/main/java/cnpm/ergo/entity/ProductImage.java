package cnpm.ergo.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_image")
@IdClass(ProductImageId.class) // Khóa chính phức hợp
public class ProductImage implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Id
    @Column(name = "productImage", columnDefinition = "NVARCHAR(500) NOT NULL")
    private String productImage;

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
