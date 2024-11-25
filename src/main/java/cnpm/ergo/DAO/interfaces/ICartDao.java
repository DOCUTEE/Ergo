package cnpm.ergo.DAO.interfaces;

<<<<<<< HEAD
import cnpm.ergo.entity.Cart;

public interface ICartDao {
    int createCart(int customerId);
    void deleteCart(int cartId);
    Cart getCartById(int cartId);
    Cart getCartByCustomerId(int customerId);
=======
import java.util.List;

import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.entity.User;

public interface ICartDao {
    void createCart(int userId);
    void updateCart(int cartId);
    void deleteCart(int cartId);
    Cart getCartByUserId(User user);
    void addItemToCart(Cart cart, CartItem cartItem);
    void removeItemFromCart(Cart cart, CartItem cartItem);
    List<CartItem> getCartItems(Cart cart);
>>>>>>> 2b1e3ae (lưu tạm)
}
