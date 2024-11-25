package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.DAO.implement.*;
import cnpm.ergo.DAO.interfaces.*;
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.service.interfaces.ICartItemService;

public class CartItemServiceImpl implements ICartItemService{
	
	private final ICartItemDao cartItemDao = new CartItemDaoImpl();

	@Override
	public void addCartItem(CartItem cartItem) {
		cartItemDao.insetCartItem(cartItem);
	}

	@Override
	public void updateCartItem(int cartId, int productId, int typeId, int quantity) {
		cartItemDao.updateCartItem(cartId, productId, typeId, quantity);
	}

	@Override
	public void deleteCartItem(int cartId, int productId, int typeId) {
		try {
            cartItemDao.deleteCartItem(cartId, productId, typeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product with ID: " + cartId + productId + typeId);
        }
		
	}

	@Override
	public List<CartItem> getCartItemsByCartId(int cartId) {
		return cartItemDao.findCartItemsByCartId(cartId);
	}

	@Override
	public double calculateTotalPrice(int cartId) {
		return cartItemDao.calculate(cartId);
	}

}
