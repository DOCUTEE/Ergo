package cnpm.ergo.service.implement;

<<<<<<< HEAD
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
=======
import java.util.List;

import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.entity.User;
import cnpm.ergo.service.interfaces.ICartService;
import cnpm.ergo.DAO.implement.*;
import cnpm.ergo.DAO.interfaces.*;

public class CartServiceImpl implements ICartService {
	
	private final ICartDao cartDao = new CartDaoImpl();

	@Override
	public void createCart(int userId) {
		cartDao.createCart(userId);
	}

	@Override
	public void updateCart(int cartId) {
		cartDao.updateCart(cartId);
	}

	@Override
	public void deleteCart(int cartId) {
		try {
            cartDao.deleteCart(cartId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product with ID: " + cartId);
        }
		
	}

	@Override
	public Cart getCartByUserId(User user) {
		return cartDao.getCartByUserId(user);
	}

	@Override
	public void addItemToCart(Cart cart, CartItem cartItem) {
		cartDao.addItemToCart(cart, cartItem);
		
	}

	@Override
	public void removeItemFromCart(Cart cart, CartItem cartItem) {
		cartDao.addItemToCart(cart, cartItem);
		
	}

	@Override
	public List<CartItem> getCartItems(Cart cart) {
		return cartDao.getCartItems(cart);
	}

>>>>>>> 2b1e3ae (lưu tạm)
}
