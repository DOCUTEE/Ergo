package cnpm.ergo.controller.customer;

import java.io.IOException;
import java.util.List;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;
import cnpm.ergo.service.interfaces.IOrderItemService;
import cnpm.ergo.service.interfaces.IOrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5	)
@WebServlet(urlPatterns = {"/customer/order"})
public class OrderController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	IOrderService orderService;
	IOrderItemService orderItemService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Order order;	
			List<OrderItem> orderItem;
			order = orderService.findById(1);
			orderItem= orderItemService.findAll(1);
			System.out.print(order);
			System.out.print(orderItem);
			req.getRequestDispatcher("/customer/views/Order.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
		
}
