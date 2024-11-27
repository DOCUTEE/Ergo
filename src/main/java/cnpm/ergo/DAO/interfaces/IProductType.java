package cnpm.ergo.DAO.interfaces;
import java.util.List;

import cnpm.ergo.entity.ProductType;

public interface IProductType {
	void insert(ProductType productType);
	void update(ProductType productType);
	void delete(int typeId);
	ProductType findById(int typeId);
	List<ProductType> findAll();
	int count();
}

