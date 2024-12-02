package cnpm.ergo.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Category;
import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.Review;
import cnpm.ergo.service.interfaces.IProductService;
import cnpm.ergo.service.implement.ProductServiceImpl;
import cnpm.ergo.service.interfaces.ICategoryService;
import cnpm.ergo.service.implement.CategoryServiceImpl;
import cnpm.ergo.service.implement.ReviewServiceImpl;
import cnpm.ergo.service.interfaces.IReviewService;

@WebServlet(urlPatterns = { "/products/detail", "/products/search" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IProductService productService = new ProductServiceImpl();
	private final ICategoryService categoryService = new CategoryServiceImpl();
	private final IReviewService reviewService = new ReviewServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        loadCommonAttributes(req);
	        String action = req.getServletPath();
	        switch (action) {
	            case "/products/detail":
	                getProductDetail(req, resp);
	                break;
	            case "/products/search":
	                int page = Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1");
	                int pageSize = Integer.parseInt(req.getParameter("size") != null ? req.getParameter("size") : "12");
	                searchProducts(req, resp, page, pageSize);
	                break;
	            default:
	                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
	    } catch (Exception e) {
	        System.err.println("Error in ProductController: " + e.getMessage());
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to process the request.");
	    }
	}

	 private void loadCommonAttributes(HttpServletRequest req) throws Exception {
	        req.setAttribute("categories", categoryService.getAllCategoriesName());
	        req.setAttribute("colors", productService.getAllColors());
	        req.setAttribute("materials", productService.getAllMaterials());
	        req.setAttribute("heights", productService.getAllHeights());
	        req.setAttribute("lengths", productService.getAllLengths());
	    }
	 private void searchProducts(HttpServletRequest req, HttpServletResponse resp, int page, int pageSize) throws ServletException, IOException {
		    try {
		        String keyword = req.getParameter("keyword");
		        String filterCategoryName = req.getParameter("categoryName");
		        HttpSession session = req.getSession();
		        session.setAttribute("keyword", keyword);
		        session.setAttribute("categoryName", filterCategoryName);
		        
		        String colorsStr = (String) session.getAttribute("colorsAsString");
		        String materialsStr = (String) session.getAttribute("materialsAsString");
		        String heightsStr = (String) session.getAttribute("heightsAsString");
		        String lengthsStr = (String) session.getAttribute("lengthsAsString");

		        String[] colors = colorsStr != null ? colorsStr.split(",") : null;
		        String[] materials = materialsStr != null ? materialsStr.split(",") : null;
		        String[] heights = heightsStr != null ? heightsStr.split(",") : null;
		        String[] lengths = lengthsStr != null ? lengthsStr.split(",") : null;
		        session.removeAttribute("colorsAsString");
		        session.removeAttribute("materialsAsString");
		        session.removeAttribute("heightsAsString");
		        session.removeAttribute("lengthsAsString");
		        
		        colors = getParameterValuesOrDefault(req, "color");
		        materials = getParameterValuesOrDefault(req, "material");
		        heights = getParameterValuesOrDefault(req, "height");
		        lengths = getParameterValuesOrDefault(req, "length");
		        String minPriceParam = req.getParameter("minPrice");
		        String maxPriceParam = req.getParameter("maxPrice");
		        Double minPrice = (minPriceParam != null && !minPriceParam.equals("null") && !minPriceParam.isEmpty()) 
		                ? Double.valueOf(minPriceParam) 
		                : null;
		        Double maxPrice = (maxPriceParam != null && !maxPriceParam.equals("null") && !maxPriceParam.isEmpty()) 
		                ? Double.valueOf(maxPriceParam) 
		                : null;

		        String filterPrice = (minPrice != null && maxPrice != null) ? minPrice + "-" + maxPrice : null;
		        session.setAttribute("selectedColors", colors);
		        session.setAttribute("selectedMaterials", materials);
		        session.setAttribute("selectedHeights", heights);
		        session.setAttribute("selectedLengths", lengths);
		        session.setAttribute("minPrice", minPrice);
		        session.setAttribute("maxPrice", maxPrice);
		        session.setAttribute("pageSize", pageSize);
		        session.setAttribute("currentPage", page);

		        System.out.println("Session ID: " + session.getId());  
		        System.out.println("Keyword from session: " + session.getAttribute("keyword"));

		        List<Product> initialProducts = productService.findByKeywordOrCategory(
		            keyword, filterCategoryName, page, pageSize
		        );
		        System.out.println("Found initial products: " + initialProducts.size()); 

		        List<Long> productIds = initialProducts.stream()
		            .map(product -> (long) product.getProductId())
		            .collect(Collectors.toList());

		        List<Product> filteredProducts = productService.applyFiltersAfterKeywordOrCategory(
		            productIds, filterPrice, colors, materials, heights, lengths, page, pageSize
		        );
		        System.out.println("Filtered products: " + filteredProducts.size()); 

		        long totalProducts = productService.getProductCount(
		            filterCategoryName, keyword, filterPrice, colors, materials, heights, lengths
		        );
		        System.out.println("Total products: " + totalProducts);
		        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
		        totalPages = totalPages > 0 ? totalPages : 1;
		        req.setAttribute("keyword", keyword);
		        req.setAttribute("categoryName", filterCategoryName);
		        req.setAttribute("products", filteredProducts);
		        req.setAttribute("currentPage", page);
		        req.setAttribute("totalPages", totalPages);
		        req.setAttribute("pageSize", pageSize);
		        req.setAttribute("totalProducts", totalProducts);
		        req.setAttribute("pageNumbers", calculatePageNumbers(page, totalPages));
		        req.getRequestDispatcher("/customer/views/product/product_search.jsp").forward(req, resp);

		    } catch (Exception e) {
		        System.err.println("Error during searchProducts: " + e.getMessage());
		        e.printStackTrace();
		        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to search products.");
		    }
		}

	private String[] getParameterValuesOrDefault(HttpServletRequest req, String paramName) {
	    String[] values = req.getParameterValues(paramName);
	    if (values == null || values.length == 0) {
	        return new String[0];  
	    }
	    return values;
	}

	private List<Integer> calculatePageNumbers(int currentPage, int totalPages) {
	    List<Integer> pageNumbers = new ArrayList<>();
	    for (int i = 1; i <= totalPages; i++) {
	        pageNumbers.add(i);
	    }
	    return pageNumbers;
	}

	private void getProductDetail(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    try {
	        String idParam = req.getParameter("id");
	        if (idParam == null || idParam.isEmpty()) {
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required");
	            return;
	        }

	        int productId = Integer.parseInt(idParam);
	        Product product = productService.getProductById(productId);
	        if (product == null) {
	            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
	            return;
	        }
	        List<Review> reviews = reviewService.getReviewsAll(productId);
	        req.setAttribute("reviews", reviews);
	        int page = 1;
	        int pageSize = 4;

	        String pageParam = req.getParameter("page");
	        if (pageParam != null && !pageParam.isEmpty()) {
	            try {
	                page = Integer.parseInt(pageParam);
	            } catch (NumberFormatException e) {
	                page = 1;
	            }
	        }

	        String sizeParam = req.getParameter("size");
	        if (sizeParam != null && !sizeParam.isEmpty()) {
	            try {
	                pageSize = Integer.parseInt(sizeParam);
	            } catch (NumberFormatException e) {
	                pageSize = 4;
	            }
	        }
	        List<Product> relatedProducts = productService.findRelatedProductsByProductId(productId, page, pageSize);
	        
	        if (relatedProducts == null || relatedProducts.isEmpty()) {
	            page = 1;
	            relatedProducts = productService.findRelatedProductsByProductId(productId, page, pageSize);
	        }

	        long totalRelatedProducts = productService.getTotalRelatedProducts(productId);
	        int totalPages = (int) Math.ceil((double) totalRelatedProducts / pageSize);

	        if (page > totalPages) {
	            page = totalPages;
	        }

	        List<Integer> pageNumbers = new ArrayList<>();
	        for (int i = 1; i <= totalPages; i++) {
	            pageNumbers.add(i);
	        }

	        req.setAttribute("product", product);
	        req.setAttribute("relatedProducts", relatedProducts);
	        req.setAttribute("totalRelatedProducts", totalRelatedProducts);
	        req.setAttribute("totalPages", totalPages);
	        req.setAttribute("currentPage", page);
	        req.setAttribute("pageNumbers", pageNumbers);

	        req.getRequestDispatcher("/customer/views/product/product_detail.jsp").forward(req, resp);
	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve product details");
	    }
	}





}
