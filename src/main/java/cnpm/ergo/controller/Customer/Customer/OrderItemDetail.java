package cnpm.ergo.controller.Customer.Customer;

import cnpm.ergo.entity.OrderItem;
import cnpm.ergo.service.implement.OrderItemServiceImpl;
import cnpm.ergo.service.interfaces.IOrderItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderItemDetailServlet", value = "/order-items")
public class OrderItemDetail extends HttpServlet {
    private IOrderItemService orderItemService;

    @Override
    public void init() throws ServletException {
        orderItemService = new OrderItemServiceImpl(); // Implement OrderItemService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy orderId từ request
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Lấy danh sách OrderItem dựa vào orderId
        List<OrderItem> orderItems = orderItemService.findAll(orderId);

        // Đưa dữ liệu vào request attribute
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("currentOrderId", orderId);

        // Forward request lại chính trang hiện tại
        request.getRequestDispatcher("/customer/views/history_order.jsp").forward(request, response);
    }
}
