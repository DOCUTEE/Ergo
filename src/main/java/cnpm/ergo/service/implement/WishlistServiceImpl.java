package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.Wishlist;
import cnpm.ergo.service.interfaces.IWishlistService;
import cnpm.ergo.DAO.implement.*;
import cnpm.ergo.DAO.interfaces.*;

public class WishlistServiceImpl implements IWishlistService {
	
	private final IWishlistDao wishlistDao = new WishlistDaoImpl();

	@Override
	public void addToWishlist(int userId, int productId) {
		wishlistDao.insert(userId, productId);
		
	}

	@Override
	public void updateWishlistStatus(Wishlist wishlist) {
		wishlistDao.update(wishlist);
		
		
	}

	@Override
	public void deleteFromWishlist(int userId, int productId) {
		try {
            wishlistDao.delete(userId, productId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product with ID: " + userId + productId);
        }
	}

	@Override
	public Wishlist getProductByUserId(int userId, int productId) {
		return wishlistDao.findByUserIdAndProductId(userId, productId);
	}

	@Override
	public List<Product> getAllWishlistByUserId(int userId) {
		return wishlistDao.findAllByUserId(userId);
	}
}
