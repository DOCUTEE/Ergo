package cnpm.ergo.controller.customer;

import cnpm.ergo.service.implement.CustomerServiceImpl;
import cnpm.ergo.service.interfaces.ICustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CustomerLoginController", value = "/customer/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ICustomerService customerService = new CustomerServiceImpl(); // Sử dụng service cho khách hàng

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Không tạo mới session nếu chưa tồn tại
        if (session != null && session.getAttribute("customer") != null) {
            // Nếu khách hàng đã đăng nhập, chuyển hướng đến trang giỏ hàng của khách hàng
            response.sendRedirect(request.getContextPath() + "/customer/home");
        } else {
            // Nếu chưa đăng nhập, hiển thị trang đăng nhập
            request.getRequestDispatcher("/customer/views/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            // Nếu email hoặc password bị null hoặc rỗng, hiển thị lỗi
            request.setAttribute("error", "Vui lòng nhập email và mật khẩu.");
            request.getRequestDispatcher("/customer/views/login.jsp").forward(request, response);
            return;
        }

        ICustomerService customerService = new CustomerServiceImpl();

        if (customerService.login(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customerService.getCustomerByEmail(email));
            response.sendRedirect(request.getContextPath() + "/customer/home");
        } else {
            request.setAttribute("error", "Email or password is incorrect.");
            request.getRequestDispatcher("/customer/views/login.jsp").forward(request, response);
        }
    }
}
