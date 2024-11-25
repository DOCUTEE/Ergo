package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "wishlist")
@NamedQuery(name = "Wishlist.findAll", query = "SELECT w FROM Wishlist w")
public class Wishlist {

    @Id
=======

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Wishlist")
public class Wishlist {

	
	@Id
>>>>>>> 2b1e3ae (lưu tạm)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlistId")
    private int wishlistId;

<<<<<<< HEAD
    public int getWishlistId() {
=======
    @ManyToOne
    @JoinColumn(name = "productId",  referencedColumnName = "productId")
    private int product;

    @ManyToOne
    @JoinColumn(name = "userId",  referencedColumnName = "userId")
    private int userId;

    @Column(name = "isDelete", columnDefinition = "BIT", nullable = false)
    private boolean isDelete;

	public int getWishlistId() {
>>>>>>> 2b1e3ae (lưu tạm)
		return wishlistId;
	}

	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
	}

<<<<<<< HEAD
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
=======
	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
>>>>>>> 2b1e3ae (lưu tạm)
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
<<<<<<< HEAD

	@Column(name = "productId", nullable = false)
    private int productId;

    @Column(name = "customerId", nullable = false)
    private int customerId;

    @Column(name = "isDelete", columnDefinition = "BIT", nullable = false)
    private boolean isDelete;

=======
    
    
>>>>>>> 2b1e3ae (lưu tạm)

}
