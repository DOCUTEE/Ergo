package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.ICartDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class CartDaoImpl implements ICartDao {


    @Override
    public void createCart(Customer customer) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setCartItems(null);
            em.persist(cart);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }

    }

    @Override
    public void updateCart(Cart cart) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(cart);
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
                em.remove(cart);
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
    public Cart getCartById(int customerId) {
        EntityManager em = JPAConfig.getEntityManager();

        try {
            String jpql = "SELECT c FROM Cart c WHERE c.customer.id = :customerId";
            TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
            query.setParameter("customerId", customerId);
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

    @Override
    public void addItemToCart(Cart cart, CartItem cartItem) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAConfig.getEntityManager();
            trans = em.getTransaction();
            trans.begin();

            Cart managedCart = em.find(Cart.class, cart.getCartId());
            if (managedCart == null) {
                throw new IllegalArgumentException("Giỏ hàng không tồn tại.");
            }

            boolean itemExists = managedCart.getCartItems().stream()
                    .anyMatch(item -> item.getProductType().getProduct().getProductId() == cartItem.getProductType().getProduct().getProductId());

            if (itemExists) {
                CartItem existingItem = managedCart.getCartItems().stream()
                        .filter(item -> item.getProductType().getProduct().getProductId() == cartItem.getProductType().getProduct().getProductId())
                        .findFirst()
                        .orElseThrow();
                existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity());
            } else {
                cartItem.setCart(managedCart);
                em.persist(cartItem);
                managedCart.getCartItems().add(cartItem);
            }
            trans.commit();
        } catch (RuntimeException e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            throw e; // Consider logging this at higher level
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void removeItemFromCart(Cart cart, CartItem cartItem) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            CartItem cartItemInCart = findCartItemInCart(cart, cartItem);
            cart.getCartItems().remove(cartItemInCart);
            em.remove(em.merge(cartItemInCart));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    private CartItem findCartItemInCart(Cart cart, CartItem cartItemToFind) {
        return cart.getCartItems().stream()
                .filter(item -> item.getCartItemId() == cartItemToFind.getCartItemId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Không có đặc điểm này"));
    }

    @Override
    public List<CartItem> getCartItems(int cartId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return findCartItemsById(em, cartId);
        } finally {
            em.close();
        }
    }
    private List<CartItem> findCartItemsById(EntityManager em, int cartId) {
        return Optional.ofNullable(em.find(Cart.class, cartId))
                .map(Cart::getCartItems)
                .orElseThrow(() -> {
                    // Log the error here
                    return new IllegalArgumentException("Giỏ hàng không tồn tại.");
                });
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(c) FROM Cart c";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public void clearCart(int customerId) {
        // Obtain an EntityManager instance
        try (EntityManager entityManager = JPAConfig.getEntityManager()) {
            // Start a transaction
            entityManager.getTransaction().begin();

            // Retrieve the cart associated with the customer
            Cart customerCart = entityManager.createQuery("SELECT c FROM Cart c WHERE c.customer.id = :customerId", Cart.class)
                    .setParameter("customerId", customerId)
                    .getSingleResult();

            // Clear the items from the cart
            customerCart.getCartItems().clear();
            entityManager.merge(customerCart); // Persist the changes

            // Commit the transaction
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }
}