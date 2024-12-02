package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;

public interface IOrderItemDao {
	void insert(Order order, OrderItem orderItem);
	void update(Order order, OrderItem orderItem);
	void delete(Order order, int orderItemId);
	List<OrderItem> findAll(int orderId);
	int count(int orderId);
	List<OrderItem> findByProductNameForOrderItem(String productName);
}
