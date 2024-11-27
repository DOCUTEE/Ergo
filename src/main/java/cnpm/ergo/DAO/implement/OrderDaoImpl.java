package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IOrderDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class OrderDaoImpl implements IOrderDao{

	@Override
	public void insert(Order order) {
		EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(order); 
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
		
	}

	@Override
	public void update(Order order) {
		EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(order); 
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }		
	}

	@Override
	public void delete(int orderId) {
		EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Order order = em.find(Order.class, orderId);
            if (order != null) {
                em.remove(order);
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
	public Order findById(int orderId) {
		EntityManager em = JPAConfig.getEntityManager();
        return em.find(Order.class, orderId);
	}

	@Override
	public List<Order> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT o FROM Order o";
        TypedQuery<Order> query = em.createQuery(jpql, Order.class);
        return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(o) FROM Order o";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
	}

	public static void main(String[] args) {
		IOrderDao o = new OrderDaoImpl();
		Order user = o.findById(1);
		System.out.print(user);
	}
	
	
}
