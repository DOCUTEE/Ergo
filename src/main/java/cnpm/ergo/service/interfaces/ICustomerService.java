package cnpm.ergo.service.interfaces;

import cnpm.ergo.entity.Customer;
import java.util.List;

public interface ICustomerService {
    public Customer getCustomerById(int id);
    public Customer getCustomerByEmail(String email);
    public List<Customer> getAllCustomers();
    public boolean insert(Customer customer);
    public void update(Customer customer);
    public void delete(int id);
    public List<Customer> search(String keyword);
    public List<Customer> findAll(int pageNo, int pageSize);
    public long count();
    public boolean login(String email, String password);
}
