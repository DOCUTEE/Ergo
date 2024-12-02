package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.DAO.implement.OrderDaoImpl;
import cnpm.ergo.DAO.interfaces.IOrderDao;
import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;
import cnpm.ergo.service.interfaces.IOrderService;

public class OrderServiceImpl implements IOrderService{
	
	private final IOrderDao orderDao = new OrderDaoImpl();
	
	@Override
	public void insert(Order order) {
		orderDao.insert(order);
		
	}

	@Override
	public void update(Order order) {
		orderDao.update(order);
		
	}

	@Override
	public void delete(int orderId) {
		try {
            orderDao.delete(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product with ID: " + orderId);
        }
		
	}
	@Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return orderDao.findByOrderId(orderId);
    }
	@Override
	public Order findById(int orderId) {
		return orderDao.findById(orderId);
	}

	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	@Override
	public int count() {
		return orderDao.count();
	}

	@Override
    public List<Order> getAllOrdersByCustomer(int customerId) {
        return orderDao.getAllOrdersByCustomer(customerId);
    }

    @Override
    public List<Order> getOrdersByCustomer(int customerId, String status) {
        return orderDao.getOrdersByCustomer(customerId, status);
    }

	
}
