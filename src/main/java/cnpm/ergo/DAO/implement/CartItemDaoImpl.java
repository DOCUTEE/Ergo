package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.ICartItemDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Cart;
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
            em.persist(cartItem); // Lưu cartItem vào database
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e; // Báo lỗi nếu không lưu được
        } finally {
            em.close();
        }
    }

    @Override
    public void updateCartItem(int cartId, CartItem cartItem) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            // Tìm Cart từ cartId
            Cart cart = em.find(Cart.class, cartId);
            // Tìm cartItem trong giỏ hàng dựa trên id
            CartItem existingCartItem = em.find(CartItem.class, cartItem.getCartItemId());
            if (existingCartItem != null) {
                existingCartItem.setQuantity(cartItem.getQuantity());
                existingCartItem.setPrice(cartItem.getPrice());
                em.merge(existingCartItem); // Cập nhật CartItem
            } else {
                throw new IllegalArgumentException("Sản phẩm không tồn tại trong giỏ hàng.");
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
    public void deleteCartItem(int cartId, CartItem cartItem) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            // Tìm cartItem trong database
            CartItem existingCartItem = em.find(CartItem.class, cartItem.getCartItemId());
            if (existingCartItem != null) {
                em.remove(existingCartItem); // Xóa CartItem
            } else {
                throw new IllegalArgumentException("Sản phẩm không tồn tại trong giỏ hàng.");
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

        try {
            // Lấy danh sách CartItem theo cartId
            String jpql = "SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId";
            TypedQuery<CartItem> query = em.createQuery(jpql, CartItem.class);
            query.setParameter("cartId", cartId);

            return query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public double calculate(int cartId) {
        EntityManager em = JPAConfig.getEntityManager();

        try {
            // Tính tổng tiền của tất cả các sản phẩm trong giỏ hàng
            String jpql = "SELECT SUM(ci.quantity * ci.price) FROM CartItem ci WHERE ci.cart.cartId = :cartId";
            TypedQuery<Double> query = em.createQuery(jpql, Double.class);
            query.setParameter("cartId", cartId);

            Double total = query.getSingleResult();
            return (total != null) ? total : 0.0;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public CartItem findCartItemById(int cartItemId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(CartItem.class, cartItemId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void checkout(Cart cart) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            List<CartItem> cartItems = cart.getCartItems();
            for (CartItem item : cartItems) {
                // Assuming some process here, such as marking the item as checked-out
                int itemId = item.getCartItemId();
                // Example: Update or perform operation on each CartItem
                CartItem managedItem = em.find(CartItem.class, itemId);
                if (managedItem != null) {
                    // Logic to process each cart item
                    // For demonstration, let's assume setting quantity to zero
                    managedItem.setQuantity(0);
                    em.merge(managedItem);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }
}
