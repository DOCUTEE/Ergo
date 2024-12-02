package cnpm.ergo.controller.customer;

import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Category;
import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.ProductImage;
import cnpm.ergo.entity.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/products/insert"})
public class ProductInsertController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/customer/views/product/product_insert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = JPAConfig.getEntityManager();

        try {
            // Lấy dữ liệu từ form
            String name = req.getParameter("name");
            String descript = req.getParameter("descript");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String color = req.getParameter("color");
            double length = Double.parseDouble(req.getParameter("length"));
            double width = Double.parseDouble(req.getParameter("width"));
            double height = Double.parseDouble(req.getParameter("height"));
            double weight = Double.parseDouble(req.getParameter("weight"));
            String material = req.getParameter("material");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String imageUrl = req.getParameter("imageUrl");

            // Kiểm tra URL hình ảnh
            if (imageUrl == null || imageUrl.isEmpty()) {
                throw new IllegalArgumentException("Image URL is required.");
            }

            // Kiểm tra trùng tên sản phẩm
            String jpql = "SELECT COUNT(p) FROM Product p WHERE LOWER(p.name) = LOWER(:name)";
            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("name", name)
                    .getSingleResult();

            if (count > 0) {
                req.setAttribute("errorMessage", "Product with the same name already exists.");
                req.getRequestDispatcher("/customer/views/product/product_insert.jsp").forward(req, resp);
                return;
            }

            // Tạo đối tượng Product
            em.getTransaction().begin();

            Product product = new Product();
            product.setName(name);
            product.setDescript(descript);
            product.setDelete(false); // Mặc định chưa bị xóa

            // Liên kết với Category
            Category category = em.find(Category.class, categoryId);
            if (category == null) {
                throw new IllegalArgumentException("Category not found with ID: " + categoryId);
            }
            product.setCategory(category);

            em.persist(product); // Lưu Product vào DB

            // Lưu ProductImage
            ProductImage productImage = new ProductImage();
            productImage.setProductImage(imageUrl);
            productImage.setProduct(product);
            em.persist(productImage);

            // Lưu ProductType
            ProductType productType = new ProductType();
            productType.setProduct(product);
            productType.setColor(color);
            productType.setLength(length);
            productType.setWidth(width);
            productType.setHeight(height);
            productType.setWeight(weight);
            productType.setMaterial(material);
            productType.setPrice(price);
            productType.setQuantity(quantity);

            em.persist(productType);

            em.getTransaction().commit();

            // Redirect đến danh sách sản phẩm và thông báo thành công
            req.setAttribute("successMessage", "Product and ProductType added successfully!");
            resp.sendRedirect(req.getContextPath() + "/products");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            req.setAttribute("errorMessage", "Error adding product: " + e.getMessage());
            req.getRequestDispatcher("/customer/views/product/product_insert.jsp").forward(req, resp);
        } finally {
            em.close();
        }
    }

}
