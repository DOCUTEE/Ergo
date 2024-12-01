package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.ICustomerDAO;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Administrator;
import cnpm.ergo.entity.Customer;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CustomerDAOImpl implements ICustomerDAO {

    @Override
    public long count() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            long count = entityManager.createQuery("SELECT COUNT(c) FROM Customer c where c.isDelete = false ", Long.class).getSingleResult();
            entityManager.getTransaction().commit();
            return count;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.getTransaction().commit();
            return customer;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.createQuery("SELECT c FROM Customer c WHERE c.email = :email and c.isDelete != true ", Customer.class)
                    .setParameter("email", email)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return customer;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c where c.isDelete = false ", Customer.class).getResultList();
            entityManager.getTransaction().commit();
            return customers;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void insert(Customer customer) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Customer customer) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        //Just set isDelete from false to true
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            customer.setIsDelete(true);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Customer> search(String keyword) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :keyword and c.isDelete = false ", Customer.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();
            entityManager.getTransaction().commit();
            return customers;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Customer> findAll(int pageNo, int pageSize) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c where c.isDelete = false ", Customer.class)
                    .setFirstResult((pageNo - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
            entityManager.getTransaction().commit();
            return customers;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
    public static void main(String[] args) {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        Customer customer = customerDAO.getCustomerByEmail("phucka004@gmail.com");
        if (customer != null) {
            System.out.println(customer.getEmail());
            System.out.println(customer.getPassword());
        } else {
            System.out.println("Customer not found.");
        }
    }
}
