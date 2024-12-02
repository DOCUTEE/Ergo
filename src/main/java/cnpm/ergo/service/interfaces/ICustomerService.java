package cnpm.ergo.service.interfaces;

import cnpm.ergo.entity.Customer;
import java.util.List;

public interface ICustomerService {
    Customer getCustomerById(int id);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    boolean insert(Customer customer);
    void update(Customer customer);
    void delete(int id);
    List<Customer> search(String keyword);
    List<Customer> findAll(int pageNo, int pageSize);
    long count();
    boolean login(String email, String password);
    boolean updateCustomerPassword(String email, String newPassword);
    Customer getCustomer(String email);
}

