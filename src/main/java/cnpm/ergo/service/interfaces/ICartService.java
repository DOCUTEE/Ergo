package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.entity.User;

public interface ICartService {
    void createCart(int userId);  
	void updateCart(int cartId);
    void deleteCart(int cartId);
    Cart getCartByUserId(User user); 
    void addItemToCart(Cart cart, CartItem cartItem); 
    void removeItemFromCart(Cart cart, CartItem cartItem); 
    List<CartItem> getCartItems(Cart cart);
}
