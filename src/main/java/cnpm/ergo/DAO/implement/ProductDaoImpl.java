package cnpm.ergo.DAO.implement;

import cnpm.ergo.entity.Category;
import cnpm.ergo.entity.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import cnpm.ergo.DAO.implement.*;

import java.util.ArrayList;
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
                product.setDelete(true); // Xóa sản phẩm
                em.merge(product);
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
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Product product = em.find(Product.class, productId); // Tìm sản phẩm theo ID
            trans.commit();
            return product;
        }
        catch (Exception ex) {
            trans.rollback();
            throw ex;
        }
    }

    @Override
    public List<Product> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        String jpql = "SELECT p FROM Product p";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        trans.commit();
        em.close();
        return query.getResultList(); // Lấy danh sách tất cả sản phẩm
    }
    public List<Product> findAllAvailable() {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        String jpql = "SELECT p FROM Product p WHERE p.isDelete == false";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        trans.commit();
        em.close();
        return query.getResultList(); // Lấy danh sách tất cả sản phẩm
    }

    @Override
    public List<Product> searchByName(String name) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        String jpql = "SELECT p FROM Product p WHERE p.name LIKE :name";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        query.setParameter("name", "%" + name + "%");
        trans.commit();
        em.close();
        return query.getResultList(); // Tìm sản phẩm theo tên
    }

    @Override
    public int count() {
            EntityManager em = JPAConfig.getEntityManager();
            EntityTransaction trans = em.getTransaction();

            try {
                trans.begin();
                String jpql = "SELECT COUNT(p) FROM Product p";
                Query query = em.createQuery(jpql);
                Long result = (Long) query.getSingleResult();
                trans.commit();
                return result.intValue(); // Đếm tổng số sản phẩm
            } catch (Exception e) {
                trans.rollback();
                throw e;
            } finally {
                em.close();
            }
    }

    @Override
    public List<Product> findProductsByPage(int offset, int limit) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class)
                .setFirstResult(offset)
                .setMaxResults(limit);
        List<Product> products = query.getResultList();
        trans.commit();
        return products;
    }
    public List<Product> findProductsAvailableByPage(int offset, int limit) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            String jpql = "SELECT p FROM Product p WHERE p.isDelete = false";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class)
                    .setFirstResult(offset).setMaxResults(limit);
            trans.commit();
            return query.getResultList(); // Lấy danh sách tất cả sản phẩm
        }
        catch (Exception e) {
            trans.rollback();
            throw e;
        }
        finally {
            em.close();
        }
    }

    @Override
    public int countAvailable() {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            String jpql = "SELECT COUNT(p) FROM Product p where p.isDelete = false";
            Query query = em.createQuery(jpql);
            Long result = (Long) query.getSingleResult();
            trans.commit();
            return result.intValue(); // Đếm tổng số sản phẩm
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }

    }

    public static void main(String[] args) {

    }
}
