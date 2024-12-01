package cnpm.ergo.controller.Admin.Product;

import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.ProductImage;
import cnpm.ergo.service.implement.CategoryServiceImpl;
import cnpm.ergo.service.implement.ProductImageServiceImpl;
import cnpm.ergo.service.implement.ProductServiceImpl;
import cnpm.ergo.service.interfaces.ICategoryService;
import cnpm.ergo.service.interfaces.IProductImageService;
import cnpm.ergo.service.interfaces.IProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/admin/product/addproduct")
@MultipartConfig
public class AddProductController extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                String productName = req.getParameter("productName");
                String description = req.getParameter("description");
                int categoryId = Integer.parseInt(req.getParameter("category"));
                List<Part> images = (List<Part>) req.getParts().stream().filter(part -> "images".equals(part.getName()) && part.getSize() > 0).toList();

                IProductService productService = new ProductServiceImpl();
                ICategoryService categoryService = new CategoryServiceImpl();
                IProductImageService ProductImageService = new ProductImageServiceImpl();

                // Tạo đối tượng Product và thiết lập giá trị
                Product product = new Product();
                product.setName(productName);
                product.setDescript(description);
                product.setCategory(categoryService.getCategoryById(categoryId));
                product.setDelete(false);

                // Thêm sản phẩm vào database và lấy id của sản phẩm mới thêm
                productService.addProduct(product);
                int productId = product.getProductId();

                // Chỉ thêm hình ảnh nếu có tệp ảnh được tải lên
                if (!images.isEmpty()) {
                        // Tạo thư mục lưu ảnh nếu chưa có
                        String uploadDir = getServletContext().getRealPath("/") + "product-images/" + productId;
                        new File(uploadDir).mkdirs();

                        // Lưu ảnh và đặt tên cho ảnh
                        for (Part image : images) {
                                String fileName = getCurrentTimeStamp() + "." + getExtension(image.getSubmittedFileName());
                                File file = new File(uploadDir, fileName);
                                image.write(file.getAbsolutePath());

                                // Lưu thông tin ảnh vào database với đường dẫn tương đối
                                ProductImage productImage = new ProductImage();
                                productImage.setProduct(product);
                                productImage.setProductImage(productId + "/" + fileName); // Đường dẫn tương đối
                                ProductImageService.addProductImage(productImage);
                        }
                }

                res.sendRedirect(req.getContextPath() + "/admin/product");
        }

        // Hàm để lấy thời gian hiện tại dưới dạng chuỗi
        private String getCurrentTimeStamp() {
                return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        }

        // Hàm để lấy phần mở rộng của file
        private String getExtension(String filename) {
                return filename.substring(filename.lastIndexOf(".") + 1);
        }
}
