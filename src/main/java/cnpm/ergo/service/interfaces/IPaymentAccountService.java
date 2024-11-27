package cnpm.ergo.service.interfaces;

import java.util.List;
import cnpm.ergo.entity.PaymentAccount;

public interface IPaymentAccountService {
    void addPaymentAccount(PaymentAccount paymentAccount);
    void updatePaymentAccount(PaymentAccount paymentAccount);
    void deletePaymentAccount(int bankId);
    PaymentAccount getPaymentAccountById(int bankId);
    List<PaymentAccount> getAllPaymentAccounts();
    List<PaymentAccount> searchPaymentAccountsByType(String type);
    int getPaymentAccountCount();
}
