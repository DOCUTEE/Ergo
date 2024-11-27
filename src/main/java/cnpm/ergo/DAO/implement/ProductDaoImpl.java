package cnpm.ergo.DAO.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import cnpm.ergo.DAO.implement.*;
import java.util.List;

import cnpm.ergo.DAO.interfaces.IProductDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Product;

public class ProductDaoImpl implements IProductDao {

    @Override
    public void insert(Product product) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(product); // Thêm mới sản phẩm
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Product product) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(product); // Cập nhật sản phẩm
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int productId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Product product = em.find(Product.class, productId);
            if (product != null) {
                em.remove(product); // Xóa sản phẩm
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
    public Product findById(int productId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Product.class, productId); // Tìm sản phẩm theo ID
    }

    @Override
    public List<Product> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT p FROM Product p";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        return query.getResultList(); // Lấy danh sách tất cả sản phẩm
    }

    @Override
    public List<Product> searchByName(String name) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT p FROM Product p WHERE p.name LIKE :name";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList(); // Tìm sản phẩm theo tên
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(p) FROM Product p";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue(); // Đếm tổng số sản phẩm
    }
}
