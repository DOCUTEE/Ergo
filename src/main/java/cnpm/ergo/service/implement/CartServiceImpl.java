package cnpm.ergo.service.implement;

import cnpm.ergo.entity.Cart;
import cnpm.ergo.service.interfaces.ICartService;
import cnpm.ergo.DAO.interfaces.ICartDao;

public class CartServiceImpl implements ICartService {
    private final ICartDao cartDao;

    public CartServiceImpl(ICartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public Cart createCart(int customerId) {
        if (!isCartExists(customerId)) {
            int cartId = cartDao.createCart(customerId);
            return cartDao.getCartById(cartId);
        }
        return cartDao.getCartByCustomerId(customerId);
    }

    @Override
    public void deleteCart(int cartId) {
        Cart cart = cartDao.getCartById(cartId);
        if (cart != null) {
            cartDao.deleteCart(cartId);
        }
    }

    @Override
    public Cart getCartByCustomerId(int customerId) {
        return cartDao.getCartByCustomerId(customerId);
    }

    @Override
    public boolean isCartExists(int customerId) {
        return cartDao.getCartByCustomerId(customerId) != null;
    }
}
