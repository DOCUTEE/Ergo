package cnpm.ergo.DAO.interfaces;

import cnpm.ergo.entity.CartItem;

import java.util.List;

public interface ICartItemDao {
    void insetCartItem(CartItem cartItem);
    void updateCartItem(int cartId, int productId, int typeId, int quantity);
    void deleteCartItem(int cartId, int productId, int typeId);
    List<CartItem> findCartItemsByCartId(int cartId);
    double calculate(int cartId);
}
