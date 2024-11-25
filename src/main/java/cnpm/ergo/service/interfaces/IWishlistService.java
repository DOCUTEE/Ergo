package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.Wishlist;

public interface IWishlistService {
	void addToWishlist(int userId, int productId);
    void updateWishlistStatus(Wishlist wishlist);
    void deleteFromWishlist(int userId, int productId);
    Wishlist getProductByUserId(int userId, int productId);
    List<Product> getAllWishlistByUserId(int userId);
}
