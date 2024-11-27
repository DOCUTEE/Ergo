package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IConversation;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Conversation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class ConversationDAOImpl implements IConversation{
	@Override
    public void insert(Conversation conversation) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(conversation); // Thêm mới sản phẩm
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
	@Override
    public void update(Conversation conversation) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(conversation); //
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int conversationId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Conversation conversation = em.find(Conversation.class, conversationId);
            if (conversation != null) {
                em.remove(conversation);
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
    public Conversation findById(int conversationId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Conversation.class, conversationId);
    }

    @Override
    public List<Conversation> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Conversation c";
        TypedQuery<Conversation> query = em.createQuery(jpql, Conversation.class);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(c) FROM Product c";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
}
