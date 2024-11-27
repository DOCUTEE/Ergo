package cnpm.ergo.service.implement;

import java.util.List;
import cnpm.ergo.service.interfaces.IPaymentAccountService;
import cnpm.ergo.DAO.implement.PaymentAccountDaoImpl;
import cnpm.ergo.DAO.interfaces.IPaymentAccountDao;
import cnpm.ergo.entity.PaymentAccount;

public class PaymentAccountServiceImpl implements IPaymentAccountService {

    private final IPaymentAccountDao paymentAccountDao = new PaymentAccountDaoImpl();

    @Override
    public void addPaymentAccount(PaymentAccount paymentAccount) {
        paymentAccountDao.insert(paymentAccount);
    }

    @Override
    public void updatePaymentAccount(PaymentAccount paymentAccount) {
        paymentAccountDao.update(paymentAccount);
    }

    @Override
    public void deletePaymentAccount(int bankId) {
        try {
            paymentAccountDao.delete(bankId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting payment account with ID: " + bankId);
        }
    }

    @Override
    public PaymentAccount getPaymentAccountById(int bankId) {
        return paymentAccountDao.findById(bankId);
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccounts() {
        return paymentAccountDao.findAll();
    }

    @Override
    public List<PaymentAccount> searchPaymentAccountsByType(String type) {
        return paymentAccountDao.searchByType(type);
    }

    @Override
    public int getPaymentAccountCount() {
        return paymentAccountDao.count();
    }
}
