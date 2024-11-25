package cnpm.ergo.service.interfaces;

import java.util.List;
import cnpm.ergo.entity.Wishlist;

public interface IWishlistService {
    void addToWishlist(int customerId, int productId);
    void updateWishlist(int wishlistId, boolean isDelete);
    void removeFromWishlist(int customerId, int productId);
    List<Wishlist> getWishlistByCustomerId(int customerId);
    boolean isProductInWishlist(int customerId, int productId);
}
