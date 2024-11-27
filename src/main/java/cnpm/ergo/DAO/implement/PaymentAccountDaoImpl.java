package cnpm.ergo.DAO.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

import cnpm.ergo.DAO.interfaces.IPaymentAccountDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.PaymentAccount;

public class PaymentAccountDaoImpl implements IPaymentAccountDao {

    @Override
    public void insert(PaymentAccount paymentAccount) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(paymentAccount); // Thêm mới tài khoản thanh toán
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(PaymentAccount paymentAccount) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(paymentAccount); // Cập nhật tài khoản thanh toán
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int bankId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            PaymentAccount paymentAccount = em.find(PaymentAccount.class, bankId);
            if (paymentAccount != null) {
                em.remove(paymentAccount); // Xóa tài khoản thanh toán
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
    public PaymentAccount findById(int bankId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(PaymentAccount.class, bankId); // Tìm tài khoản thanh toán theo ID
    }

    @Override
    public List<PaymentAccount> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT p FROM PaymentAccount p";
        TypedQuery<PaymentAccount> query = em.createQuery(jpql, PaymentAccount.class);
        return query.getResultList(); // Lấy danh sách tất cả tài khoản thanh toán
    }

    @Override
    public List<PaymentAccount> searchByType(String type) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT p FROM PaymentAccount p WHERE p.type LIKE :type";
        TypedQuery<PaymentAccount> query = em.createQuery(jpql, PaymentAccount.class);
        query.setParameter("type", "%" + type + "%");
        return query.getResultList(); // Tìm tài khoản thanh toán theo loại
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(p) FROM PaymentAccount p";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue(); // Đếm tổng số tài khoản thanh toán
    }
}
