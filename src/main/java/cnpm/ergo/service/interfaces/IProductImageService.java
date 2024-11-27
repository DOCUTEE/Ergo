package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.ProductImage;

public interface IProductImageService {
	void addProductImage(ProductImage productImage);
	void deleteProductImage(ProductImage productImage);
	List<ProductImage> getProductImagesByProductId(int productId);
	List<ProductImage> getAllProductImages();
	int getProductImageCount();
}
