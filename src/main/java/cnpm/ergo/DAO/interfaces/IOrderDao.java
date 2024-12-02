package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;

public interface IOrderDao {
	void insert(Order order);
	void update(Order order);
	void delete(int orderId);
	Order findById(int orderId);
	List<Order> findAll();
	int count();
	List<Order> getOrdersByCustomer(int customerId, String status);
	List<Order> getAllOrdersByCustomer(int customerId);
	List<OrderItem> findByOrderId(int orderId);

}
