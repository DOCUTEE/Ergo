package cnpm.ergo.service.interfaces;
import java.util.List;
import cnpm.ergo.entity.Product;

public interface IProductService {
	void addProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(int productId);
	Product getProductById(int productId);
	List<Product> getAllProducts(int page, int size);
	int getProductCount();
	List<String> getAllColors();
	List<String> getAllMaterials();
	List<Double> getAllHeights();
	List<Double> getAllLengths();
	List<Product> findByKeywordOrCategory(String keyword, String categoryName, int page, int size);
	List<Product> applyFiltersAfterKeywordOrCategory(List<Long> productIdsLong, String filterPrice, String[] colors,
			String[] materials, String[] heights, String[] lengths, int page, int size);
	long getProductCount(String keyword, String categoryName, String filterPrice, String[] colors, String[] materials,
			String[] heights, String[] lengths);
	List<Product> findRelatedProductsByProductId(int productId, int page, int pageSize);
	long getTotalRelatedProducts(int productId);
}
