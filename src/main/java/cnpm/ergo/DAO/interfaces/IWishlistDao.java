package cnpm.ergo.DAO.interfaces;

<<<<<<< HEAD
=======
import cnpm.ergo.entity.Product;
>>>>>>> 2b1e3ae (lưu tạm)
import cnpm.ergo.entity.Wishlist;

import java.util.List;

public interface IWishlistDao {
<<<<<<< HEAD
    void addWishlistItem(int customerId, int productId);
    void updateWishlist(Wishlist wishlist);
    void removeWishlistItem(int customerId, int productId);
    Wishlist getWishlistById(int wishlistId);
    List<Wishlist> getWishlistByCustomerId(int customerId);
    boolean isProductInWishlist(int customerId, int productId);
=======
    void insert(int userId, int productId);
    void update(Wishlist wishlist);
    void delete(int userId, int productId);
    Wishlist findByUserIdAndProductId(int userId, int productId);
    List<Product> findAllByUserId(int userId);
>>>>>>> 2b1e3ae (lưu tạm)
}
