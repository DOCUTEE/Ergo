package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IOrderItemDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

public class OrderItemDaoImpl implements IOrderItemDao{

	@Override
	public void insert(Order order, OrderItem orderItem) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();

	        // Liên kết Order với OrderItem
	        orderItem.setOrder(order);

	        // Thêm mới OrderItem vào cơ sở dữ liệu
	        em.persist(orderItem);

	        trans.commit();
	    } catch (Exception e) {
	        trans.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
		
	}

	@Override
	public void update(Order order, OrderItem orderItem) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();

	        // Truy vấn OrderItem dựa trên orderId và productId
	        OrderItem existingOrderItem = em.createQuery(
	                "SELECT oi FROM OrderItem oi WHERE oi.order = :order AND oi.productType = :productType", 
	                OrderItem.class)
	            .setParameter("order", order)
	            .setParameter("productType", orderItem.getProductType())
	            .getSingleResult();

	        existingOrderItem.setQuantity(orderItem.getQuantity());
	        existingOrderItem.setPrice(orderItem.getPrice());

	        em.merge(existingOrderItem);

	        trans.commit();
	    } catch (NoResultException e) {
	        trans.rollback();
	        throw new IllegalArgumentException("OrderItem does not exist for the given Order and ProductType.");
	    } catch (Exception e) {
	        trans.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }		
	}

	@Override
	public void delete(Order order, int orderItemId) {
		EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();

	        OrderItem orderItem = em.createQuery(
	                "SELECT oi FROM OrderItem oi WHERE oi.order = :order AND oi.productType.productId = :orderItemId", 
	                OrderItem.class)
	            .setParameter("order", order)
	            .setParameter("orderItemId", orderItemId)
	            .getSingleResult();

	        em.remove(orderItem);

	        trans.commit();
	    } catch (NoResultException e) {
	        trans.rollback();
	        throw new IllegalArgumentException("OrderItem does not exist for the given Order and orderItemId.");
	    } catch (Exception e) {
	        trans.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
		
	}

	@Override
	public List<OrderItem> findAll(int orderId) {
		EntityManager em = JPAConfig.getEntityManager();

	    try {
	        return em.createQuery(
	                "SELECT oi FROM OrderItem oi WHERE oi.order.orderId=:orderId",
	                OrderItem.class)
	            .setParameter("orderId", orderId)
	            .getResultList();
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to retrieve OrderItems for orderId: " + orderId, e);
	    } finally {
	        em.close();
	    }
	}

	@Override
	public int count(int orderId) {
		EntityManager em = JPAConfig.getEntityManager();

	    try {
	        Long count = em.createQuery(
	                "SELECT COUNT(oi) FROM OrderItem oi WHERE oi.order.orderId = :orderId", 
	                Long.class)
	            .setParameter("orderId", orderId)
	            .getSingleResult();

	        return count.intValue();
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to count OrderItems for orderId: " + orderId, e);
	    } finally {
	        em.close();
	    }
	}

	
	
}
