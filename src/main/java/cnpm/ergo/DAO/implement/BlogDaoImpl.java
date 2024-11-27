package cnpm.ergo.DAO.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

import cnpm.ergo.DAO.interfaces.IBlogDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Blog;

public class BlogDaoImpl implements IBlogDao {

    @Override
    public void insert(Blog blog) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(blog); // Thêm mới blog
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Blog blog) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(blog); // Cập nhật blog
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int blogId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Blog blog = em.find(Blog.class, blogId);
            if (blog != null) {
                em.remove(blog); // Xóa blog
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
    public Blog findById(int blogId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Blog.class, blogId); // Tìm blog theo ID
    }

    @Override
    public List<Blog> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT b FROM Blog b";
        TypedQuery<Blog> query = em.createQuery(jpql, Blog.class);
        return query.getResultList(); // Lấy danh sách tất cả blog
    }

    @Override
    public List<Blog> searchByTitle(String title) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT b FROM Blog b WHERE b.blogTitle LIKE :title";
        TypedQuery<Blog> query = em.createQuery(jpql, Blog.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList(); // Tìm blog theo tiêu đề
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(b) FROM Blog b";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue(); // Đếm tổng số blog
    }
}
