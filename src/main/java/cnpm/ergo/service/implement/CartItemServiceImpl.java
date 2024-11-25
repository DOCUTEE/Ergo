package cnpm.ergo.service.implement;

<<<<<<< HEAD
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.service.interfaces.ICartItemService;
import cnpm.ergo.DAO.interfaces.ICartItemDao;

import java.util.List;

public class CartItemServiceImpl implements ICartItemService {
    private final ICartItemDao cartItemDao;

    public CartItemServiceImpl(ICartItemDao cartItemDao) {
        this.cartItemDao = cartItemDao;
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        int cartId = cartItem.getCart().getCartId();
        CartItem existingItem = cartItemDao.getCartItem(cartId, cartItem.getProductId(), cartItem.getTypeId());
        if (existingItem != null) {
  
            int newQuantity = existingItem.getQuantity() + cartItem.getQuantity();
            cartItemDao.updateCartItemQuantity(cartId, cartItem.getProductId(), cartItem.getTypeId(), newQuantity);
        } else {
          
            cartItemDao.addCartItem(cartItem);
        }
    }

    @Override
    public void updateCartItemQuantity(int cartId, int productId, int typeId, int quantity) {
        if (quantity > 0) {
            cartItemDao.updateCartItemQuantity(cartId, productId, typeId, quantity);
        } else {
            cartItemDao.deleteCartItem(cartId, productId, typeId);
        }
    }

    @Override
    public void removeCartItem(int cartId, int productId, int typeId) {
        cartItemDao.deleteCartItem(cartId, productId, typeId);
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {
        return cartItemDao.getCartItemsByCartId(cartId);
    }

    @Override
    public double calculateTotalPrice(int cartId) {
        List<CartItem> cartItems = cartItemDao.getCartItemsByCartId(cartId);
        return cartItems.stream()
                        .mapToDouble(item -> item.getPrice() * item.getQuantity())
                        .sum();
    }
=======
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

>>>>>>> 2b1e3ae (lưu tạm)
}
