package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.IReviewDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ReviewDaoImpl implements IReviewDao {

    @Override
    public void insertReview(Review review) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(review);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}


