package cnpm.ergo.service.implement;

<<<<<<< HEAD
import cnpm.ergo.entity.Wishlist;
import cnpm.ergo.service.interfaces.IWishlistService;
import cnpm.ergo.DAO.interfaces.IWishlistDao;

import java.util.List;

public class WishlistServiceImpl implements IWishlistService {
    private final IWishlistDao wishlistDao;

    public WishlistServiceImpl(IWishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @Override
    public void addToWishlist(int customerId, int productId) {
        if (!wishlistDao.isProductInWishlist(customerId, productId)) {
            wishlistDao.addWishlistItem(customerId, productId);
        }
    }

    @Override
    public void updateWishlist(int wishlistId, boolean isDelete) {
        Wishlist wishlist = wishlistDao.getWishlistById(wishlistId);
        if (wishlist != null) {
            wishlist.setDelete(isDelete);
            wishlistDao.updateWishlist(wishlist);
        }
    }

    @Override
    public void removeFromWishlist(int customerId, int productId) {
        if (wishlistDao.isProductInWishlist(customerId, productId)) {
            wishlistDao.removeWishlistItem(customerId, productId);
        }
    }

    @Override
    public List<Wishlist> getWishlistByCustomerId(int customerId) {
        return wishlistDao.getWishlistByCustomerId(customerId);
    }

    @Override
    public boolean isProductInWishlist(int customerId, int productId) {
        return wishlistDao.isProductInWishlist(customerId, productId);
    }
=======
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
>>>>>>> 2b1e3ae (lưu tạm)
}
