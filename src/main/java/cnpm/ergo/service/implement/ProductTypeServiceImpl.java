package cnpm.ergo.service.implement;


import java.util.List;

import cnpm.ergo.service.interfaces.IProductTypeService;
import cnpm.ergo.DAO.implement.*;
import cnpm.ergo.DAO.interfaces.*;
import cnpm.ergo.entity.ProductType;
public class ProductTypeServiceImpl implements IProductTypeService {

    private final IProductType productTypeDao = new ProductTypeDaoImpl();

    @Override
    public void addProductType(ProductType productType) {
        productTypeDao.insert(productType);
    }

    @Override
    public void updateProductType(ProductType productType) {
        productTypeDao.update(productType);
    }

    @Override
    public void deleteProductType(int typeId) {
        try {
            productTypeDao.delete(typeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product type with ID: " + typeId);
        }
    }

    @Override
    public ProductType getProductTypeById(int typeId) {
        return productTypeDao.findById(typeId);
    }

    @Override
    public List<ProductType> getAllProductTypes() {
        return productTypeDao.findAll();
    }

    @Override
    public int getProductTypeCount() {
        return productTypeDao.count();
    }
}

