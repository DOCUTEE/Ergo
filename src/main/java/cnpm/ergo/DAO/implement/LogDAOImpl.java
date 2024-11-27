package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.LogDAO;
import cnpm.ergo.entity.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class LogDAOImpl implements LogDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    @Override
    public boolean addLog(Log log) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(log); // Thêm mới log vào database
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Log getLogById(int logId) {
        EntityManager em = emf.createEntityManager();
        Log log = em.find(Log.class, logId);
        em.close();
        return log;
    }

    @Override
    public boolean updateLog(Log log) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(log); // Cập nhật log vào database
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteLog(int logId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            String log = em.find(String.class, logId);
            em.remove(log); // Xóa log khỏi database
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
        EntityManager em = emf.createEntityManager();

        // Start transaction
        em.getTransaction().begin();

        // Create new log
        Log log = new Log();
        log.setUser(new UserDAOImpl().getUserById(1));
        log.setDateLog(LocalDateTime.now());
        log.setContent("User login");


        // Commit transaction
        em.getTransaction().commit();

        // Close the EntityManager and EntityManagerFactory
        em.close();
        emf.close();

        System.out.println("Log saved successfully!");
    }

}
