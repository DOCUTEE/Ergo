package cnpm.ergo.DAO.interfaces;

import cnpm.ergo.entity.Cart;

public interface ICartDao {
    int createCart(int customerId);
    void deleteCart(int cartId);
    Cart getCartById(int cartId);
    Cart getCartByCustomerId(int customerId);
}
