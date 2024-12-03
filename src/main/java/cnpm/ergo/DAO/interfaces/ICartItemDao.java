package cnpm.ergo.DAO.interfaces;

import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;

import java.util.List;

public interface ICartItemDao {
    void insetCartItem(CartItem cartItem);
    void updateCartItem(int cartId, CartItem cartItem);
    void deleteCartItem(int cartId, CartItem cartItem);
    List<CartItem> findCartItemsByCartId(int cartId);
    double calculate(int cartId);
    CartItem findCartItemById(int cartItemId);
    void checkout(Cart cart);
}
