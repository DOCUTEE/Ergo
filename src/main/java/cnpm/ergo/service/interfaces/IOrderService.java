package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.Order;

public interface IOrderService {
	void insert(Order order);
	void update(Order order);
	void delete(int orderId);
	Order findById(int orderId);
	List<Order> findAll();
	int count();

}
