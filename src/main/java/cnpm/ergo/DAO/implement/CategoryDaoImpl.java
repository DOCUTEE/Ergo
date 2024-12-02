package cnpm.ergo.DAO.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

import cnpm.ergo.DAO.interfaces.ICategoryDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Category;

public class CategoryDaoImpl implements ICategoryDao {

    @Override
    public void insert(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(category); // Thêm mới category
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(category); // Cập nhật category
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int categoryId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Category category = em.find(Category.class, categoryId);
            if (category != null) {
                em.remove(category); // Xóa category
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
    public Category findById(int categoryId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Category.class, categoryId); // Tìm category theo ID
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c";
        TypedQuery<Category> query = em.createQuery(jpql, Category.class);
        return query.getResultList(); // Lấy danh sách tất cả category
    }

    @Override
    public List<Category> searchByName(String name) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.categoryName LIKE :name";
        TypedQuery<Category> query = em.createQuery(jpql, Category.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList(); // Tìm category theo tên
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(c) FROM Category c";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue(); // Đếm tổng số category
    }
    @Override
    public List<Category> findAllCategoryName() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c";
            return em.createQuery(jpql, Category.class).getResultList();
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {
        CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
        // find by id
        System.out.println(categoryDaoImpl.findById(1));
        // insert
        Category category = new Category();
        category.setCategoryName("LAPTOP");
        categoryDaoImpl.insert(category);

    }
}
