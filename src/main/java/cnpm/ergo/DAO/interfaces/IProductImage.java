package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.ProductImage;

public interface IProductImage {
	void insert(ProductImage productImage);
	void delete(ProductImage productImage);
	List<ProductImage> findByProductId(int productId);
	int count();
	List<ProductImage> findAll();
}
