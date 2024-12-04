package cnpm.ergo.service.implement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import cnpm.ergo.DAO.implement.CartDaoImpl;
import cnpm.ergo.DAO.implement.OrderDaoImpl;
import cnpm.ergo.DAO.implement.OrderItemDaoImpl;
import cnpm.ergo.DAO.interfaces.ICartDao;
import cnpm.ergo.DAO.interfaces.IOrderDao;
import cnpm.ergo.DAO.interfaces.IOrderItemDao;
import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;
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

	private static IOrderDao orderDao1;
	private static IOrderItemDao orderItemDao;
	private static ICartDao cartDao;

	public OrderServiceImpl() {
		this.orderDao1 = new OrderDaoImpl();
		this.orderItemDao = new OrderItemDaoImpl();
		this.cartDao = new CartDaoImpl();
	}

	public static boolean checkout(int cartId) {
		// Bước 1: Lấy thông tin giỏ hàng từ DB
		Cart cart = cartDao.getCartById(cartId);
		if (cart == null) {
			throw new IllegalArgumentException("Giỏ hàng không tồn tại.");
		}

		// Bước 2: Tạo đơn hàng mới
		Order order = new Order();
		order.setCustomer(cart.getCustomer()); // Gán thông tin khách hàng
		LocalDate currentDate = LocalDate.now();
		order.setOrderDate(Date.valueOf(currentDate));
		orderDao1.insert(order); // Lưu đơn hàng vào cơ sở dữ liệu

		// Bước 3: Chuyển các CartItem thành OrderItem
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order); // Gán đơn hàng cho OrderItem
			orderItem.setProductType(cartItem.getProductType()); // Lấy sản phẩm từ CartItem
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(cartItem.getPrice());
			orderItemDao.insert(order, orderItem); // Lưu OrderItem vào cơ sở dữ liệu
		}

		// Bước 4: Xóa các CartItem trong giỏ hàng sau khi thanh toán
		cartDao.clearCart(cart.getCustomer().getUserId()); // Xóa tất cả các CartItem

		return true; // Trả về true nếu thanh toán thành công
	}



	
}
