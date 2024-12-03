package cnpm.ergo.controller.customer;

import cnpm.ergo.entity.Customer;
import cnpm.ergo.service.implement.CustomerServiceImpl;
import cnpm.ergo.service.interfaces.ICustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterController", value = "/customer/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ICustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hiển thị form đăng ký
        request.getRequestDispatcher("/customer/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phonenumber");
        String password = request.getParameter("password");

        try {
            // Kiểm tra dữ liệu đầu vào
            if (name == null || email == null || phone == null || password == null ||
                    name.trim().isEmpty() || email.trim().isEmpty() || phone.trim().isEmpty() || password.trim().isEmpty()) {
                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
                request.getRequestDispatcher("/customer/views/register.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng Customer
            Customer customer = new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setPassword(password);

            // Kiểm tra và chèn khách hàng
            if (!customerService.insert(customer)) {
                // Email đã tồn tại
                request.setAttribute("error", "Email đã tồn tại. Vui lòng thử email khác.");
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/customer/views/register.jsp").forward(request, response);
                return;
            }

            // Đăng ký thành công, chuyển sang trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/customer/login");
        } catch (Exception e) {
            // Xử lý lỗi và quay lại trang đăng ký
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi trong quá trình xử lý. Vui lòng thử lại!");
            request.getRequestDispatcher("/customer/views/register.jsp").forward(request, response);
        }
    }
}
