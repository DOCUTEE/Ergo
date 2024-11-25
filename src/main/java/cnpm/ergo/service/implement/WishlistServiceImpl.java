package cnpm.ergo.service.implement;

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
}
