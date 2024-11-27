package cnpm.ergo.DAO.interfaces;

import java.util.List;
import cnpm.ergo.entity.PaymentAccount;

public interface IPaymentAccountDao {
    void insert(PaymentAccount paymentAccount);
    void update(PaymentAccount paymentAccount);
    void delete(int bankId);
    PaymentAccount findById(int bankId);
    List<PaymentAccount> findAll();
    List<PaymentAccount> searchByType(String type);
    int count();
}
