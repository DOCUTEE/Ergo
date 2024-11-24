package cnpm.ergo.DAO.implement;

import java.sql.Date;
import java.util.List;

import cnpm.ergo.DAO.interfaces.IMessage;
import cnpm.ergo.DAO.interfaces.UserDAO;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Conversation;
import cnpm.ergo.entity.Message;
import cnpm.ergo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class MessageDAOImpl implements IMessage{
	@Override
    public void insert(Message message) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(message); // Thêm mới sản phẩm
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
	@Override
    public void update(Message message) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(message); //
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int messageId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Message message = em.find(Message.class, messageId);
            if (message != null) {
                em.remove(message);
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
    public Message findById(int messageId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Message.class, messageId);
    }

    @Override
    public List<Message> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT m FROM Message m";
        TypedQuery<Message> query = em.createQuery(jpql, Message.class);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(m) FROM Message m";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }

    public static void main(String[] args) {
        //insert message
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUserById(1);

        Conversation conversation = new Conversation();


        Message message = new Message();
        message.setContent("Hello");
        message.setSender(user);
        message.setTimestamp(Date.valueOf("2021-10-10"));
        MessageDAOImpl messageDAOImpl = new MessageDAOImpl();
        messageDAOImpl.insert(message);

    }
}
