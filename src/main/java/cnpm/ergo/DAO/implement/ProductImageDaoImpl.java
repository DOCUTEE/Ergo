package cnpm.ergo.DAO.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IProductImage;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.ProductImage;
import cnpm.ergo.DAO.interfaces.*;

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
            ProductImage foundImage = em.find(ProductImage.class, productImage.getProductImageId());
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

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(pi) FROM ProductImage pi";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        return query.getSingleResult().intValue(); // Đếm số lượng hình ảnh
    }

    @Override
    public List<ProductImage> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT pi FROM ProductImage pi";
        TypedQuery<ProductImage> query = em.createQuery(jpql, ProductImage.class);
        return query.getResultList(); // Lấy danh sách tất cả hình ảnh
    }
    public static void main(String[] args) {
        IProductImage productImageDao = new ProductImageDaoImpl();
        //List all product images
        List<ProductImage> productImages = productImageDao.findAll();
        for (ProductImage productImage : productImages) {
            System.out.println(productImage.getProductImage());
        }
    }
}
