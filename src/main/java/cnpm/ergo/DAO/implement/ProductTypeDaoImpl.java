package cnpm.ergo.DAO.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IProductType;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.ProductType;

public class ProductTypeDaoImpl implements IProductType {

    @Override
    public void insert(ProductType productType) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(productType); // Thêm mới loại sản phẩm
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(ProductType productType) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(productType); // Cập nhật loại sản phẩm
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int typeId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            ProductType productType = em.find(ProductType.class, typeId);
            if (productType != null) {
                em.remove(productType); // Xóa loại sản phẩm
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
    public ProductType findById(int typeId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(ProductType.class, typeId); // Tìm loại sản phẩm theo ID
    }

    @Override
    public List<ProductType> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT pt FROM ProductType pt";
        TypedQuery<ProductType> query = em.createQuery(jpql, ProductType.class);
        return query.getResultList(); // Lấy danh sách tất cả loại sản phẩm
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(pt) FROM ProductType pt";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue(); // Đếm tổng số loại sản phẩm
    }
}
