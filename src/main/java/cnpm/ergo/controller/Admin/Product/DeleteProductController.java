package cnpm.ergo.controller.Admin.Product;

import cnpm.ergo.DAO.implement.ProductDaoImpl;
import cnpm.ergo.DAO.interfaces.IProductDao;
import cnpm.ergo.service.implement.ProductServiceImpl;
import cnpm.ergo.service.interfaces.IProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/product/deleteproduct")
public class DeleteProductController extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                int productId = Integer.parseInt(req.getParameter("productId"));
                IProductService productService = new ProductServiceImpl();

                // Xóa sản phẩm từ database
                productService.deleteProduct(productId);

                res.sendRedirect(req.getContextPath() + "/admin/product");
        }
}
