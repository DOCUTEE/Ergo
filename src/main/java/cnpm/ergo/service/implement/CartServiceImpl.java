package cnpm.ergo.service.implement;

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

}
