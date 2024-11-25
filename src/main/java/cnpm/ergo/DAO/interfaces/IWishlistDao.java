package cnpm.ergo.DAO.interfaces;

import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.Wishlist;

import java.util.List;

public interface IWishlistDao {
    void insert(int userId, int productId);
    void update(Wishlist wishlist);
    void delete(int userId, int productId);
    Wishlist findByUserIdAndProductId(int userId, int productId);
    List<Product> findAllByUserId(int userId);
}
