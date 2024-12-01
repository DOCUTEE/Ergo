package cnpm.ergo.controller.Admin.Product;

import cnpm.ergo.entity.Category;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig(
        fileSizeThreshold = 1024 * 10,  // 10 KB
        maxFileSize = 1024 * 1024 * 100,  // 2 MB (example size)
        maxRequestSize = 1024 * 1024 * 200 // 5 MB (example size)
)
@WebServlet(urlPatterns = "/admin/product/editproduct")
public class EditProductController extends HttpServlet {
        private IProductService productService = new ProductServiceImpl();
        private ICategoryService categoryService = new CategoryServiceImpl();
        private IProductImageService productImageService = new ProductImageServiceImpl();

        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                // Retrieve product details from req
                Part productIdPart = req.getPart("productId");
                Part productNamePart = req.getPart("productName");
                Part descriptionPart = req.getPart("description");
                Part categoryPart = req.getPart("category");

                int productId = Integer.parseInt(new String(productIdPart.getInputStream().readAllBytes()));
                String productName = new String(productNamePart.getInputStream().readAllBytes());
                String description = new String(descriptionPart.getInputStream().readAllBytes());
                int categoryId = Integer.parseInt(new String(categoryPart.getInputStream().readAllBytes()));

                // Update product details
                Product product = productService.getProductById(productId);
                product.setName(productName);
                product.setDescript(description);
                product.setCategory(categoryService.getCategoryById(categoryId));

                List<Part> imageParts = req.getParts().stream()
                        .filter(part -> "newImages".equals(part.getName()) && part.getSize() > 0)
                        .collect(Collectors.toList());

                // Handle new images (optional, for example purposes)
                if (!imageParts.isEmpty()) {
                        // Create the directory to save images if it doesn't exist
                        String uploadDir = getServletContext().getRealPath("/") + "product-images/" + productId;
                        new File(uploadDir).mkdirs();

                        // Save images and set filenames
                        for (Part image : imageParts) {
                                String fileName = getCurrentTimeStamp() + "." + getExtension(image.getSubmittedFileName());
                                File file = new File(uploadDir, fileName);
                                image.write(file.getAbsolutePath());

                                // Save image info into the database with relative path
                                ProductImage productImage = new ProductImage();
                                productImage.setProduct(product);
                                productImage.setProductImage(productId + "/" + fileName); // Relative path
                                productImageService.addProductImage(productImage);
                        }
                }

                productService.updateProduct(product);

                // Redirect to product management page
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
