package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.RoleDAO;
import cnpm.ergo.entity.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");

    @Override
    public boolean addRole(Role role) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(role); // Thêm mới role vào database
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
    public List<Role> getAllRoles() {
        EntityManager em = emf.createEntityManager();
        List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
        em.close();
        return roles;
    }
    @Override
    public Role getRoleById(int roleId) {
        EntityManager em = emf.createEntityManager();
        Role role = em.find(Role.class, roleId);
        em.close();
        return role;
    }
    @Override
    public boolean updateRole(Role role) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(role); // Cập nhật role vào database
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
    public boolean deleteRole(int roleId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Role role = em.find(Role.class, roleId);
            if (role != null) {
                em.remove(role); // Xóa role khỏi database
            }
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
        RoleDAO roleDAO = new RoleDAOImpl();
        Role role = new Role();
        role.setRoleName("Manager");
        roleDAO.addRole(role);

        List<Role> roles = roleDAO.getAllRoles();
        for (Role r : roles) {
            System.out.println(r.getRoleId() + " - " + r.getRoleName());
        }

        Role role2 = roleDAO.getRoleById(1);
        if (role2 != null) {
            System.out.println(role2.getRoleId() + " - " + role2.getRoleName());
            role2.setRoleName("Admin");
            roleDAO.updateRole(role2);
        }
        roleDAO.deleteRole(1);
        roles = roleDAO.getAllRoles();
        for (Role r : roles) {
            System.out.println(r.getRoleId() + " - " + r.getRoleName());
        }
    }
}
