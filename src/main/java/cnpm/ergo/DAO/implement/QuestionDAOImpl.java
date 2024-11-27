package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IQuestion;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class QuestionDAOImpl implements IQuestion{
	@Override
    public void insert(Question question) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(question);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
	@Override
    public void update(Question question) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(question); 
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int questionId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Question question = em.find(Question.class, questionId);
            if (question != null) {
                em.remove(question);
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
    public Question findById(int questionId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Question.class, questionId);
    }

    @Override
    public List<Question> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT q FROM Question q";
        TypedQuery<Question> query = em.createQuery(jpql, Question.class);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(q) FROM Question c";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
}
