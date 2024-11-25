package cnpm.ergo.DAO.implement;

<<<<<<< HEAD
import cnpm.ergo.DAO.interfaces.ICartItemDao;
import cnpm.ergo.entity.CartItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class CartItemDaoImpl implements ICartItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addCartItem(CartItem cartItem) {
        entityManager.persist(cartItem);
    }

    @Override
    public void updateCartItemQuantity(int cartId, int productId, int typeId, int quantity) {
        CartItem cartItem = getCartItem(cartId, productId, typeId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            entityManager.merge(cartItem);
        }
    }

    @Override
    public void deleteCartItem(int cartId, int productId, int typeId) {
        CartItem cartItem = getCartItem(cartId, productId, typeId);
        if (cartItem != null) {
            entityManager.remove(cartItem);
        }
    }

    @Override
    public CartItem getCartItem(int cartId, int productId, int typeId) {
        TypedQuery<CartItem> query = entityManager.createQuery(
            "SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.productId = :productId AND ci.typeId = :typeId", 
            CartItem.class
        );
        query.setParameter("cartId", cartId);
        query.setParameter("productId", productId);
        query.setParameter("typeId", typeId);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {
        TypedQuery<CartItem> query = entityManager.createQuery(
            "SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId", 
            CartItem.class
        );
        query.setParameter("cartId", cartId);
        return query.getResultList();
    }
=======
import java.util.List;

import cnpm.ergo.DAO.interfaces.ICartItemDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.CartItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CartItemDaoImpl implements ICartItemDao {

	@Override
	public void insetCartItem(CartItem cartItem) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        em.persist(cartItem); 
	        trans.commit();
	    } catch (Exception e) {
	        trans.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
		
	}

	@Override
	public void updateCartItem(int cartId, int productId, int typeId, int quantity) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        String jpql = "SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.productId = :productId AND ci.typeId = :typeId";
	        TypedQuery<CartItem> query = em.createQuery(jpql, CartItem.class);
	        query.setParameter("cartId", cartId);
	        query.setParameter("productId", productId);
	        query.setParameter("typeId", typeId);

	        CartItem cartItem = query.getSingleResult();
	        if (cartItem != null) {
	            cartItem.setQuantity(quantity); // Cập nhật số lượng
	            em.merge(cartItem); // Lưu 
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
	public void deleteCartItem(int cartId, int productId, int typeId) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        String jpql = "SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.productId = :productId AND ci.typeId = :typeId";
	        TypedQuery<CartItem> query = em.createQuery(jpql, CartItem.class);
	        query.setParameter("cartId", cartId);
	        query.setParameter("productId", productId);
	        query.setParameter("typeId", typeId);

	        CartItem cartItem = query.getSingleResult();
	        if (cartItem != null) {
	            em.remove(cartItem); // Xóa CartItem
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
	public List<CartItem> findCartItemsByCartId(int cartId) {
		EntityManager em = JPAConfig.getEntityManager();
	    String jpql = "SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId";
	    TypedQuery<CartItem> query = em.createQuery(jpql, CartItem.class);
	    query.setParameter("cartId", cartId);

	    return query.getResultList();
	}

	@Override
	public double calculate(int cartId) {
		EntityManager em = JPAConfig.getEntityManager();
	    String jpql = "SELECT SUM(ci.price * ci.quantity) FROM CartItem ci WHERE ci.cart.cartId = :cartId";
	    TypedQuery<Double> query = em.createQuery(jpql, Double.class);
	    query.setParameter("cartId", cartId);

	    Double total = query.getSingleResult(); // Tính tổng giá trị
	    return total != null ? total : 0.0;
	}

>>>>>>> 2b1e3ae (lưu tạm)
}
