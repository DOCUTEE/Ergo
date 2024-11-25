package cnpm.ergo.DAO.interfaces;

import cnpm.ergo.entity.CartItem;

import java.util.List;

public interface ICartItemDao {
    void addCartItem(CartItem cartItem);
    void updateCartItemQuantity(int cartId, int productId, int typeId, int quantity);
    void deleteCartItem(int cartId, int productId, int typeId);
    CartItem getCartItem(int cartId, int productId, int typeId);
    List<CartItem> getCartItemsByCartId(int cartId);
}
