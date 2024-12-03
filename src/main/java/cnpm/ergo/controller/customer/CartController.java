package cnpm.ergo.controller.customer;

import cnpm.ergo.DAO.implement.CartDaoImpl;
import cnpm.ergo.DAO.implement.CartItemDaoImpl;
import cnpm.ergo.DAO.interfaces.ICartDao;
import cnpm.ergo.DAO.interfaces.ICartItemDao;
import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.entity.Customer;
import cnpm.ergo.service.implement.ProductTypeServiceImpl;
import cnpm.ergo.service.interfaces.IProductTypeService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartController", value = "/customer/cart")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICartDao cartDao;
    private ICartItemDao cartItemDao;
    private IProductTypeService productTypeService;
    private ObjectMapper mapper;

    @Override
    public void init() {
        cartDao = new CartDaoImpl();
        cartItemDao = new CartItemDaoImpl();
        mapper = new ObjectMapper();
        productTypeService = new ProductTypeServiceImpl();
    }

    private Customer getCustomerIdFromSession(HttpServletRequest request) {
        return (Customer) request.getSession().getAttribute("customer");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = getCustomerIdFromSession(request);
        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/customer/login");
            return;
        }

        Cart cart = cartDao.getCartById(customer.getUserId());
        if (cart == null || cart.getCartItems().isEmpty()) {
            request.setAttribute("message", "Giỏ hàng của bạn rỗng");
        } else {
            request.setAttribute("cartItems", cart.getCartItems());
            request.setAttribute("cartId", cart.getCartId());
        }
        request.getRequestDispatcher("/customer/views/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer customer = getCustomerIdFromSession(request);
        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/customer/login");
            return;
        }
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(400, "action is invalid!");
            return;
        }

        try {
            switch (action) {
                case "updateQuantity":
                    updateCartItem(request, response);
                    break;
                case "deleteItem":
                    deleteCartItem(request, response);
                    break;
                case "checkout":
                    checkout(request, response);
                    break;
                default:
                    response.sendError(400, "action is unsupported!");
                    break;
            }
        } catch (Exception e) {
            response.sendError(400, "action is invalid!");
        }
    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        if (quantity <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
        }

        CartItem cartItem = cartItemDao.findCartItemById(cartItemId);
        if (cartItem == null) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại trong giỏ hàng.");
        }


        if (quantity > (cartItem.getProductType().getQuantity())){
            Map<String, Object> result = new HashMap<>();
            result.put("status", "quantityExceed");
            result.put("action", "updateQuantity");
            result.put("oldQuantity", cartItem.getQuantity());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(mapper.writeValueAsString(result));
            response.getWriter().flush();
            return;
        }

        cartItem.setQuantity(quantity);
        cartItemDao.updateCartItem(cartItemId, cartItem);

        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("action", "updateQuantity");
        result.put("oldQuantity", cartItem.getQuantity());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(mapper.writeValueAsString(result));
        response.getWriter().flush();
    }

    private void deleteCartItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
        CartItem cartItem = cartItemDao.findCartItemById(cartItemId);
        Customer customer = getCustomerIdFromSession(request);
        Cart cart = cartDao.getCartById(customer.getUserId());

        if (cartItem == null) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại trong giỏ hàng.");
        }

        cartItemDao.deleteCartItem(cartItemId, cartItem);

        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("action", "deleteItem");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(mapper.writeValueAsString(result));
        response.getWriter().flush();
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = getCustomerIdFromSession(request);
        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/customer/login");
            return;
        }

        Cart cart = cartDao.getCartById(customer.getUserId());
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException("Giỏ hàng của bạn rỗng.");
        }

        cartItemDao.checkout(cart);
        cartDao.clearCart(customer.getUserId());

        request.setAttribute("message", "Thanh toán thành công!");
        response.sendRedirect(request.getContextPath() + "/customer/cart");
    }
}
