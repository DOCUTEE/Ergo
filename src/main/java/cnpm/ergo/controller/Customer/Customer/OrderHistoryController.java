package cnpm.ergo.controller.Customer.Customer;

import cnpm.ergo.entity.*;
import cnpm.ergo.service.implement.OrderItemServiceImpl;
import cnpm.ergo.service.interfaces.IOrderService;
import cnpm.ergo.service.implement.OrderServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "OrderHistoryController", value = "/customer/order-history")
public class OrderHistoryController extends HttpServlet {
    private IOrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderServiceImpl(); // Triển khai OrderService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kiểm tra user đã đăng nhập chưa
        if (request.getSession().getAttribute("customer") == null) {
            response.sendRedirect(request.getContextPath() + "/customer/login");
            return;
        }

        try {
            // Lấy userId từ session
            int userId = ((Customer) request.getSession().getAttribute("customer")).getUserId();
//            System.out.println("userID: " + userId);
            // Lấy danh sách lịch sử đơn hàng
            List<Order> orderHistory = orderService.findAll();
//            System.out.println("orderHistory: " + orderHistory);
//            orderHistory.forEach(order -> {
//                if (order.getCustomer() != null) {
//                    System.out.println("Order ID: " + order.getOrderId() + ", Customer ID: " + order.getCustomer().getUserId());
//                } else {
//                    System.out.println("Order ID: " + order.getOrderId() + " has no customer.");
//                }
//            });

            List<Order> filteredOrders = orderHistory.stream()
                    .filter(order -> order.getCustomer().getUserId() == userId)
                    .toList();
            OrderItemServiceImpl itemservice = new OrderItemServiceImpl();

            Map<Integer, List<OrderItem>> orderItemsMap = new HashMap<>();
            for (Order order : filteredOrders) {
                int orderId = order.getOrderId();
                List<OrderItem> orderItems = itemservice.findAll(orderId);
                orderItemsMap.put(orderId, orderItems);
            }
            HttpSession session = request.getSession();
            // Gửi dữ liệu tới JSP
            session.setAttribute("orderHistory", filteredOrders);

            request.getRequestDispatcher("/customer/views/history_order.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức tạo dữ liệu demo
}
