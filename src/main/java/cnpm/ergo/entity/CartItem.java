package cnpm.ergo.entity;

import jakarta.persistence.*;
<<<<<<< HEAD

=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
>>>>>>> 2b1e3ae (lưu tạm)
@Entity
@Table(name = "CartItem")
@NamedQuery(name = "CartItem.findAll", query = "SELECT ci FROM CartItem ci")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItemId")
    private int cartItemId;

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;

    @Column(name = "typeId", nullable = false)
    private int typeId;

    @Column(name = "productId", nullable = false)
    private int productId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

	public int getCartItemId() {
=======
    public int getCartItemId() {
>>>>>>> 2b1e3ae (lưu tạm)
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

<<<<<<< HEAD
=======
	@ManyToOne
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;

    @Column(name = "typeId", nullable = false)
    private int typeId;

    @Column(name = "productId", nullable = false)
    private int productId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

>>>>>>> 2b1e3ae (lưu tạm)
}
