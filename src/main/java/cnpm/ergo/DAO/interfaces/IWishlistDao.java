package cnpm.ergo.DAO.interfaces;

import cnpm.ergo.entity.Wishlist;

import java.util.List;

public interface IWishlistDao {
    void addWishlistItem(int customerId, int productId);
    void updateWishlist(Wishlist wishlist);
    void removeWishlistItem(int customerId, int productId);
    Wishlist getWishlistById(int wishlistId);
    List<Wishlist> getWishlistByCustomerId(int customerId);
    boolean isProductInWishlist(int customerId, int productId);
}
