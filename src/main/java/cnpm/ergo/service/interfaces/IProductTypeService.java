package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.ProductType;

public interface IProductTypeService {
	void addProductType(ProductType productType);
	void updateProductType(ProductType productType);
	void deleteProductType(int typeId);
	ProductType getProductTypeById(int typeId);
	List<ProductType> getAllProductTypes();
	int getProductTypeCount();
}
