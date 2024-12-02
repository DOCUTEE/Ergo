package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.IUserDAO;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.User;
import java.util.List;
import jakarta.persistence.EntityManager;


public class UserDAOImpl implements IUserDAO {

    @Override
    public boolean addUser(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user); // Thêm mới user vào database
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
    public List<User> getAllUsers() {
        EntityManager em = JPAConfig.getEntityManager();
        List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
        em.close();
        return users;
    }

    @Override
    public User getUserById(int userId) {
        EntityManager em = JPAConfig.getEntityManager();
        User user = em.find(User.class, userId);
        em.close();
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user); // Cập nhật user vào database
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
    public boolean deleteUser(int userId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            em.remove(user); // Xóa user khỏi database
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
    public User getUserByEmail(String email) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            // Query to find the user by email
            User user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if no user is found or there is an error
        } finally {
            em.close();
        }
    }



    public static void main(String[] args) {

    }

}
