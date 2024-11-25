package cnpm.ergo.service.interfaces;

import java.util.List;
import cnpm.ergo.entity.CartItem;

public interface ICartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(int cartId, int productId, int typeId, int quantity);
    void deleteCartItem(int cartId, int productId, int typeId);
    List<CartItem> getCartItemsByCartId(int cartId);
    double calculateTotalPrice(int cartId);
}
