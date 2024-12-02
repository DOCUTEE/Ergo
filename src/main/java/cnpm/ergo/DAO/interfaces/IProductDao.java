package cnpm.ergo.DAO.interfaces;

import java.util.List;

import org.hibernate.query.Page;

import cnpm.ergo.entity.Category;
import cnpm.ergo.entity.Product;
public interface IProductDao {
	void insert(Product product);
	void update(Product product);
	void delete(int productId);
	Product findById(int productId);
	int count(String categoryId);
	int count();
	List<Product> findAllList(int page, int size);
	List<String> findAllColors();
	List<String> findAllMaterials();
	List<Double> findAllHeights();
	List<Double> findAllLengths();
	List<Product> findByKeywordOrCategory(String keyword, String categoryName, int page, int size);
	List<Product> applyFiltersAfterKeywordOrCategory(List<Long> productIdsLong, String filterPrice, String[] colors,
			String[] materials, String[] heights, String[] lengths, int page, int size);
	long Count(String keyword, String categoryName, String filterPrice, String[] colors, String[] materials,
			String[] heights, String[] lengths);
	List<Product> findRelatedProductsByProductId(int productId, int page, int pageSize);
	long getTotalRelatedProducts(int productId);
}

