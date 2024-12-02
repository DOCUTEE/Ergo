package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;


public interface IOrderItemService{
	void insert(Order order, OrderItem orderItem);
	void update(Order order, OrderItem orderItem);
	void delete(Order order, int orderItemId);
	List<OrderItem> findAll(int orderId);
	int count(int orderId);
	List<OrderItem> findByProductName(String productName);
}
