package cnpm.ergo.service.interfaces;

import java.util.List;
import cnpm.ergo.entity.CartItem;

public interface ICartItemService {
    void addCartItem(CartItem cartItem);
<<<<<<< HEAD
    void updateCartItemQuantity(int cartId, int productId, int typeId, int quantity);
    void removeCartItem(int cartId, int productId, int typeId);
=======
    void updateCartItem(int cartId, int productId, int typeId, int quantity);
    void deleteCartItem(int cartId, int productId, int typeId);
>>>>>>> 2b1e3ae (lưu tạm)
    List<CartItem> getCartItemsByCartId(int cartId);
    double calculateTotalPrice(int cartId);
}
