package cnpm.ergo.service.interfaces;

import java.util.List;
<<<<<<< HEAD
import cnpm.ergo.entity.Wishlist;

public interface IWishlistService {
    void addToWishlist(int customerId, int productId);
    void updateWishlist(int wishlistId, boolean isDelete);
    void removeFromWishlist(int customerId, int productId);
    List<Wishlist> getWishlistByCustomerId(int customerId);
    boolean isProductInWishlist(int customerId, int productId);
=======

import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.Wishlist;

public interface IWishlistService {
	void addToWishlist(int userId, int productId);
    void updateWishlistStatus(Wishlist wishlist);
    void deleteFromWishlist(int userId, int productId);
    Wishlist getProductByUserId(int userId, int productId);
    List<Product> getAllWishlistByUserId(int userId);
>>>>>>> 2b1e3ae (lưu tạm)
}
