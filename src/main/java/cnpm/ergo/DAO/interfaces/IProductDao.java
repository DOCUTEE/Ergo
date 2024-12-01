package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.Product;
public interface IProductDao {
	void insert(Product product);
	void update(Product product);
	void delete(int productId);
	Product findById(int productId);
	List<Product> findAll();
	List<Product> searchByName(String name);
	int count();

	List<Product> findProductsByPage(int offset, int limit);
	List<Product> findProductsAvailableByPage(int offset, int limit);
	int countAvailable();
}

