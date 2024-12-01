package cnpm.ergo.service.interfaces;
import java.util.List;
import cnpm.ergo.entity.Product;

public interface IProductService {
	void addProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(int productId);
	Product getProductById(int productId);
	List<Product> getAllProducts();
	List<Product> searchProductsByName(String name);
	int getProductCount();
	int getProductAvailableCount();

	List<Product> getProductsByPage(int offset, int limit);
	List<Product> getProductsAvailableByPage(int offset, int limit);
}
