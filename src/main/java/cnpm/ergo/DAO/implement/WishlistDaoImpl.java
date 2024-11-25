package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.IWishlistDao;
import cnpm.ergo.entity.Wishlist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class WishlistDaoImpl implements IWishlistDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addWishlistItem(int customerId, int productId) {
        Wishlist wishlist = new Wishlist();
        wishlist.setCustomerId(customerId);
        wishlist.setProductId(productId);
        wishlist.setDelete(false);
        entityManager.persist(wishlist);
    }

    @Override
    public void updateWishlist(Wishlist wishlist) {
        entityManager.merge(wishlist);
    }

    @Override
    public void removeWishlistItem(int customerId, int productId) {
        TypedQuery<Wishlist> query = entityManager.createQuery(
            "SELECT w FROM Wishlist w WHERE w.customerId = :customerId AND w.productId = :productId", 
            Wishlist.class
        );
        query.setParameter("customerId", customerId);
        query.setParameter("productId", productId);
        List<Wishlist> result = query.getResultList();
        if (!result.isEmpty()) {
            entityManager.remove(result.get(0));
        }
    }

    @Override
    public Wishlist getWishlistById(int wishlistId) {
        return entityManager.find(Wishlist.class, wishlistId);
    }

    @Override
    public List<Wishlist> getWishlistByCustomerId(int customerId) {
        TypedQuery<Wishlist> query = entityManager.createQuery(
            "SELECT w FROM Wishlist w WHERE w.customerId = :customerId AND w.isDelete = false", 
            Wishlist.class
        );
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    @Override
    public boolean isProductInWishlist(int customerId, int productId) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(w) FROM Wishlist w WHERE w.customerId = :customerId AND w.productId = :productId AND w.isDelete = false", 
            Long.class
        );
        query.setParameter("customerId", customerId);
        query.setParameter("productId", productId);
        return query.getSingleResult() > 0;
    }
}
