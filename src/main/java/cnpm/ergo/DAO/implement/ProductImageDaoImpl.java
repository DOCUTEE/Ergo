package cnpm.ergo.DAO.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IProductImage;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.ProductImage;

public class ProductImageDaoImpl implements IProductImage {

    @Override
    public void insert(ProductImage productImage) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(productImage); // Thêm mới hình ảnh sản phẩm
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(ProductImage productImage) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            ProductImage foundImage = em.find(ProductImage.class, productImage);
            if (foundImage != null) {
                em.remove(foundImage); // Xóa hình ảnh sản phẩm
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
    public List<ProductImage> findByProductId(int productId) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT pi FROM ProductImage pi WHERE pi.product.productId = :productId";
        TypedQuery<ProductImage> query = em.createQuery(jpql, ProductImage.class);
        query.setParameter("productId", productId);
        return query.getResultList(); // Tìm hình ảnh theo productId
    }

    public List<ProductImage> findAll() {
        // chưa implement
        return null;
    }
    public int count() {
        // chưa implement
        return 0;
    };
}
