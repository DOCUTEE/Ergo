package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.ICartDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CartDaoImpl implements ICartDao {

	@Override
	public void createCart(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        User user = em.find(User.class, userId); 
	        if (user != null) {
	            Cart cart = new Cart();
	            cart.setUserId(user);
	            em.persist(cart); 
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
	public void updateCart(int cartId) {
		    EntityManager em = JPAConfig.getEntityManager();
		    EntityTransaction trans = em.getTransaction();

		    try {
		        trans.begin();
		        Cart cart = em.find(Cart.class, cartId); // Lấy Cart từ cartId
		        if (cart != null) {		            
		            em.merge(cart); // Cập nhật Cart
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
	public void deleteCart(int cartId) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        Cart cart = em.find(Cart.class, cartId); 
	        if (cart != null) {
	            em.remove(cart); // Xóa Cart
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
	public Cart getCartByUserId(User user) {
		EntityManager em = JPAConfig.getEntityManager();
	    String jpql = "SELECT c FROM Cart c WHERE c.userId = :user";
	    TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
	    query.setParameter("user", user);

	    try {
	        return query.getSingleResult(); // Lấy giỏ hàng của người dùng
	    } catch (Exception e) {
	        return null; 
	    } finally {
	        em.close();
	    }
	}

	@Override
	public void addItemToCart(Cart cart, CartItem cartItem) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        cartItem.setCart(cart); // Gắn Cart vào CartItem
	        em.persist(cartItem); // Thêm mới CartItem
	        trans.commit();
	    } catch (Exception e) {
	        trans.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
		
	}

	@Override
	public void removeItemFromCart(Cart cart, CartItem cartItem) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        CartItem item = em.find(CartItem.class, cartItem.getCartItemId()); // Tìm CartItem cần xóa
	        if (item != null && item.getCart().equals(cart)) {
	            em.remove(item); // Xóa CartItem
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
	public List<CartItem> getCartItems(Cart cart) {
		EntityManager em = JPAConfig.getEntityManager();
	    String jpql = "SELECT ci FROM CartItem ci WHERE ci.cart = :cart";
	    TypedQuery<CartItem> query = em.createQuery(jpql, CartItem.class);
	    query.setParameter("cart", cart);

	    return query.getResultList();
	}

}
