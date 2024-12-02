package cnpm.ergo.controller.customer;

import cnpm.ergo.DAO.interfaces.ICustomerDAO;
import cnpm.ergo.service.implement.CustomerServiceImpl;
import cnpm.ergo.service.implement.OTPService;
import cnpm.ergo.service.implement.UserServiceImpl;
import cnpm.ergo.service.interfaces.ICustomerService;
import cnpm.ergo.service.interfaces.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {
        "/customer/forgot-password",
        "/customer/verify-otp",
        "/customer/reset-password"
})
public class ForgotPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final OTPService otpService = new OTPService(); // OTP service
    private final IUserService userService = new UserServiceImpl(); // User service
    private final ICustomerService customerService = new CustomerServiceImpl(); // Customer service


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/customer/forgot-password":
                request.getRequestDispatcher("/customer/views/forgot-password.jsp").forward(request, response);
                break;
            case "/customer/verify-otp":
                request.getRequestDispatcher("/customer/views/verify-otp.jsp").forward(request, response);
                break;
            case "/customer/reset-password":
                request.getRequestDispatcher("/customer/views/reset-password.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/customer/forgot-password":
                handleForgotPassword(request, response);
                break;
            case "/customer/verify-otp":
                handleVerifyOTP(request, response);
                break;
            case "/customer/reset-password":
                handleResetPassword(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    private void handleForgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Handling forgot password request...");

        // Retrieve the email parameter from the request
        String email = request.getParameter("email");
        System.out.println("Received email parameter: " + email);

        // Check if the email is empty or null
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email is empty. Prompting user to enter an email.");
            request.setAttribute("error", "Please enter your email address.");
            request.getRequestDispatcher("/customer/views/forgot-password.jsp").forward(request, response);
            return;
        }

        // Check if the email exists in the system
        System.out.println("Checking if email exists in the system...");
        if (customerService.getCustomerByEmail(email) != null) {
            System.out.println("Email found: " + email);
            try {
                // Generate and send the OTP
                System.out.println("Generating OTP...");
                String otp = otpService.generateOTP();
                System.out.println("Generated OTP: " + otp);
                System.out.println("Sending OTP email...");
                otpService.sendOTPEmail(email, otp);

                // Save OTP and email to session
                System.out.println("Saving OTP and email to session...");
                HttpSession session = request.getSession();
                session.setAttribute("otp", otp);
                session.setAttribute("email", email);

                // Redirect to verify OTP page
                System.out.println("Redirecting to verify OTP page...");
                response.sendRedirect(request.getContextPath() + "/customer/verify-otp");
            } catch (Exception e) {
                // Log the exception details
                System.err.println("Failed to send OTP email:");
                e.printStackTrace(System.err);

                // Show error message on the UI
                request.setAttribute("error", "Failed to send OTP. Please try again.");
                request.getRequestDispatcher("/customer/views/forgot-password.jsp").forward(request, response);
            }
        } else {
            // Email not found
            System.out.println("No account found with email: " + email);
            request.setAttribute("error", "No account found with that email.");
            request.getRequestDispatcher("/customer/views/forgot-password.jsp").forward(request, response);
        }

    }



    private void handleVerifyOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredOTP = request.getParameter("otp");
        HttpSession session = request.getSession();
        String sessionOTP = (String) session.getAttribute("otp");

        if (enteredOTP != null && enteredOTP.equals(sessionOTP)) {
            response.sendRedirect(request.getContextPath() + "/customer/reset-password");
        } else {
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("/customer/views/verify-otp.jsp").forward(request, response);
        }
    }

    private void handleResetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

//        UserServiceImpl service = new UserServiceImpl();

        System.out.println("Received new password: " + newPassword);
        System.out.println("Received confirm password: " + confirmPassword);
        System.out.println("Email from session: " + email);

        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match. Please try again.");
            request.getRequestDispatcher("/customer/views/reset-password.jsp").forward(request, response);
            return;
        }

        try {
            // Call user service to update the password
            boolean result = customerService.updateCustomerPassword(email, newPassword);
            System.out.println("Password updated: " + result);

            if (result) {
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/customer/login");
            } else {
                request.setAttribute("error", "Failed to reset password. Please try again.");
                request.getRequestDispatcher("/customer/views/reset-password.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to update password: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update password." + e.getMessage());
            request.setAttribute("error", "Failed to reset password. Please try again.");
            request.getRequestDispatcher("/customer/views/reset-password.jsp").forward(request, response);
        }
    }
}
