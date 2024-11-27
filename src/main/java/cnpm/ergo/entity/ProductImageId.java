package cnpm.ergo.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductImageId implements Serializable {

    private int product;
    private String productImage;
    public ProductImageId() {}

    public ProductImageId(int product, String productImage) {
        this.product = product;
        this.productImage = productImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImageId that = (ProductImageId) o;
        return product == that.product && Objects.equals(productImage, that.productImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productImage);
    }
}

