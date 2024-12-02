package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;

public interface IOrderService {
	void insert(Order order);
	void update(Order order);
	void delete(int orderId);
	Order findById(int orderId);
	List<Order> findAll();
	int count();
	List<Order> getAllOrdersByCustomer(int customerId);
	List<Order> getOrdersByCustomer(int customerId, String status);
	List<OrderItem> getOrderItemsByOrderId(int orderId);
}
