package cnpm.ergo.controller.customer;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;
import cnpm.ergo.service.implement.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/customer/managerorder"})
public class OrderManagerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderServiceImpl orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        int userId = 1;  
        List<Order> orders;
        if (status == null || status.equals("all")) {
            orders = orderService.getAllOrdersByCustomer(userId); 
        } else {
            orders = orderService.getOrdersByCustomer(userId, status); 
        }
        for (Order order : orders) {
            if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
                List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(order.getOrderId());
                order.setOrderItems(orderItems); 
            }
        }

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/customer/views/delivery/order_manager.jsp").forward(req, resp);

        String orderIdStr = req.getParameter("orderId");
        if (orderIdStr != null) {
            try {
                int orderId = Integer.parseInt(orderIdStr);
                Order order = orderService.findById(orderId);
                if (order == null) {
                    resp.sendRedirect(req.getContextPath() + "/error");
                    return;
                }
                if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
                    List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
                    order.setOrderItems(orderItems);
                }
                req.setAttribute("order", order);
                req.getRequestDispatcher("/customer/views/delivery/order_detail.jsp").forward(req, resp);

            } catch (NumberFormatException e) {
                resp.sendRedirect(req.getContextPath() + "/error");
            }
        }
    }

}
