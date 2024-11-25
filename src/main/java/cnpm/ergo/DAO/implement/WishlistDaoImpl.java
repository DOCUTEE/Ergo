package cnpm.ergo.DAO.implement;

<<<<<<< HEAD
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
=======
import java.util.List;

import cnpm.ergo.DAO.interfaces.IWishlistDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.Wishlist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class WishlistDaoImpl implements IWishlistDao {

	@Override
	public void insert(int userId, int productId) {
		 EntityManager em = JPAConfig.getEntityManager();
	     EntityTransaction trans = em.getTransaction();
	     try {
	    	    trans.begin(); 
	    	    Wishlist wishlist = new Wishlist();
	    	    wishlist.setUserId(userId); 
	    	    wishlist.setProduct(productId); 
	    	    em.persist(wishlist);

	    	    trans.commit(); 
	    	} catch (Exception e) {
	    	    e.printStackTrace(); 
	    	    if (trans.isActive()) {
	    	        trans.rollback(); 
	    	    }
	    	} finally {
	    	    em.close(); 
	    	}
	}

	@Override
	public void update(Wishlist wishlist) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        em.merge(wishlist); // Cập nhật thông tin của wishlist
	        trans.commit();
	    } catch (Exception e) {
	        trans.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
		
	}

	@Override
	public void delete(int userId, int productId) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        // Tìm user và sp cụ thể
	        String jpql = "SELECT w FROM Wishlist w WHERE w.user.userId = :userId AND w.product.productId = :productId";
	        TypedQuery<Wishlist> query = em.createQuery(jpql, Wishlist.class);
	        query.setParameter("userId", userId);
	        query.setParameter("productId", productId);
	        Wishlist wishlist = query.getSingleResult(); 

	        if (wishlist != null) {
	            em.remove(wishlist); // Xóa
	        }
	        trans.commit();
	    } catch (Exception e) {
	        trans.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
		
	}

	@Override
	public Wishlist findByUserIdAndProductId(int userId, int productId) {
		EntityManager em = JPAConfig.getEntityManager();
	    String jpql = "SELECT w FROM Wishlist w WHERE w.user.userId = :userId AND w.product.productId = :productId";
	    TypedQuery<Wishlist> query = em.createQuery(jpql, Wishlist.class);
	    query.setParameter("userId", userId);
	    query.setParameter("productId", productId);

	    try {
	        return query.getSingleResult(); 
	    } catch (Exception e) {
	        return null; 
	    } finally {
	        em.close();
	    }
	}

	@Override
	public List<Product> findAllByUserId(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
	    String jpql = "SELECT w.product FROM Wishlist w WHERE w.user.userId = :userId AND w.isDelete = false";
	    TypedQuery<Product> query = em.createQuery(jpql, Product.class);
	    query.setParameter("userId", userId);

	    return query.getResultList();
	}

>>>>>>> 2b1e3ae (lưu tạm)
}
