package cnpm.ergo.service.implement;

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
}
