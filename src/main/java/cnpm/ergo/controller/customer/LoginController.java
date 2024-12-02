package cnpm.ergo.controller.customer;

import cnpm.ergo.service.implement.AdministratorServiceImpl;
import cnpm.ergo.service.implement.OTPService;
import cnpm.ergo.service.implement.CustomerServiceImpl;
import cnpm.ergo.service.interfaces.IAdministratorService;
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
    private final OTPService otpService = new OTPService();  // OTP service

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Không tạo mới session nếu chưa tồn tại
        if (session != null && session.getAttribute("customer") != null) {
            // Nếu khách hàng đã đăng nhập, chuyển hướng đến trang chủ
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
            session.setAttribute("customer", customerService.getCustomer(email));
            response.sendRedirect(request.getContextPath() + "/customer/home");
        } else {
            request.setAttribute("error", "Email or password is incorrect.");
            request.getRequestDispatcher("/customer/views/login.jsp").forward(request, response);
        }
    }

    // New method to handle forgot password
    protected void doForgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            // If email is empty, show error message
            request.setAttribute("error", "Please enter your email address.");
            request.getRequestDispatcher("/customer/views/forgot-password.jsp").forward(request, response);
            return;
        }

        // Check if the email exists in the system
        if (customerService.getCustomerByEmail(email) != null) {
            try {
                String otp = otpService.generateOTP();  // Generate OTP
                otpService.sendOTPEmail(email, otp);   // Send OTP email

                // Store OTP and email in session for verification later
                HttpSession session = request.getSession();
                session.setAttribute("otp", otp);
                session.setAttribute("email", email);

                // Redirect to OTP verification page
                response.sendRedirect(request.getContextPath() + "/customer/verify-otp");
            } catch (Exception e) {
                // Handle failure to send OTP email
                request.setAttribute("error", "Failed to send OTP. Please try again.");
                request.getRequestDispatcher("/customer/views/forgot-password.jsp").forward(request, response);
            }
        } else {
            // If email is not found, show error message
            request.setAttribute("error", "No account found with that email.");
            request.getRequestDispatcher("/customer/views/forgot-password.jsp").forward(request, response);
        }
    }

    // Verify OTP for password reset
    protected void doVerifyOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredOTP = request.getParameter("otp");
        HttpSession session = request.getSession();
        String sessionOTP = (String) session.getAttribute("otp");

        if (enteredOTP != null && enteredOTP.equals(sessionOTP)) {
            // OTP is correct, redirect to reset password page
            response.sendRedirect(request.getContextPath() + "/customer/reset-password");
        } else {
            // OTP is incorrect, show error message
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("/customer/views/verify-otp.jsp").forward(request, response);
        }
    }
}
