package cnpm.ergo.DAO.implement;

import org.springframework.stereotype.Repository;

import cnpm.ergo.DAO.interfaces.ICartDao;
import cnpm.ergo.entity.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Repository
public class CartDaoImpl implements ICartDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int createCart(int customerId) {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        entityManager.persist(cart);
        entityManager.flush(); 
        return cart.getCartId();
    }

    @Override
    public void deleteCart(int cartId) {
        Cart cart = getCartById(cartId);
        if (cart != null) {
            entityManager.remove(cart);
        }
    }

    @Override
    public Cart getCartById(int cartId) {
        return entityManager.find(Cart.class, cartId);
    }

    @Override
    public Cart getCartByCustomerId(int customerId) {
        TypedQuery<Cart> query = entityManager.createQuery(
            "SELECT c FROM Cart c WHERE c.customerId = :customerId", 
            Cart.class
        );
        query.setParameter("customerId", customerId);
        return query.getResultStream().findFirst().orElse(null);
    }
}
