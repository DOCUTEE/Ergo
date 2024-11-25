package cnpm.ergo.entity;

<<<<<<< HEAD
import jakarta.persistence.*;

=======
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
>>>>>>> 2b1e3ae (lưu tạm)
@Entity
@Table(name = "cart")
@NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c")
public class Cart {

<<<<<<< HEAD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private int cartId;

    @Column(name = "customerId", nullable = false)
    private int customerId;

=======
>>>>>>> 2b1e3ae (lưu tạm)
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

<<<<<<< HEAD
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

=======
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private int cartId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userId;
    
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

>>>>>>> 2b1e3ae (lưu tạm)
}
