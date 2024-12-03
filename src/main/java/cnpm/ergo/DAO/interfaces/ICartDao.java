package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.entity.Customer;


public interface ICartDao {
    void createCart(Customer customer);
    void updateCart(Cart cart);
    void deleteCart(int cartId);
    Cart getCartById(int customerId);
    void addItemToCart(Cart cart, CartItem cartItem);
    void removeItemFromCart(Cart cart, CartItem cartItem);
    List<CartItem> getCartItems(int cartId);
    int count();
    void clearCart(int customerId);
}
