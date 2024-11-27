package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.RoleDAO;
import cnpm.ergo.DAO.interfaces.UserDAO;
import cnpm.ergo.entity.Role;
import cnpm.ergo.entity.User;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class UserDAOImpl implements UserDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    @Override
    public boolean addUser(User user) {
        EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();
        List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
        em.close();
        return users;
    }

    @Override
    public User getUserById(int userId) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, userId);
        em.close();
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();
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

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
        EntityManager em = emf.createEntityManager();

        // Start transaction
        em.getTransaction().begin();

        Role role = new RoleDAOImpl().getRoleById(2);
        // Create new user
        User user = new User();
        user.setName("Nguyen Van B");
        user.setPassword("123456");
        user.setEmail("OK@gmail.com");
        user.setPhone("0123456789");
        user.setAddress("Ha Noi");
        user.setGender("Nam");
        user.setRole(role);
        user.setStatus("Active");
        user.setIsDelete(false);

        // Save user
        em.persist(user);

        // Commit transaction
        em.getTransaction().commit();

        // Close entity manager
        em.close();

        // Close entity manager factory
        emf.close();

        System.out.println("User saved successfully!");
    }

}
