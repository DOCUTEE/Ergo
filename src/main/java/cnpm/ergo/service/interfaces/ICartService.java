package cnpm.ergo.service.interfaces;

import cnpm.ergo.entity.Cart;

public interface ICartService {
    Cart createCart(int customerId);
    void deleteCart(int cartId);
    Cart getCartByCustomerId(int customerId);
    boolean isCartExists(int customerId);
}
