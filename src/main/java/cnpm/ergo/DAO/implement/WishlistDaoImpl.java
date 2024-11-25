package cnpm.ergo.DAO.implement;

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

}
