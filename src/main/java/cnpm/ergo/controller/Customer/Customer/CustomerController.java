package cnpm.ergo.controller.Customer.Customer;

import cnpm.ergo.entity.Customer;
import cnpm.ergo.service.implement.CustomerServiceImpl;
import cnpm.ergo.service.interfaces.ICustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CustomerController1", value = "/customer/info")
public class CustomerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_user = 94;
        //int pageSize = 10;
        if (request.getParameter("id") != null) {
            id_user = Integer.parseInt(request.getParameter("id"));
//            System.out.println("id_user param"+ id_user);
        }
        ICustomerService customerService = new CustomerServiceImpl();
        HttpSession session = request.getSession();
        Customer customer = customerService.getCustomerById(id_user);
//        System.out.println("Customer Info: " + customer);
        response.getWriter().println("Customer Data: " + customer);
        session.setAttribute("customer", customer);
        request.getRequestDispatcher("/customer/views/info_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}