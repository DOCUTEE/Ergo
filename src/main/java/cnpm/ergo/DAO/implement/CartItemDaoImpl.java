package cnpm.ergo.DAO.implement;

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
}
