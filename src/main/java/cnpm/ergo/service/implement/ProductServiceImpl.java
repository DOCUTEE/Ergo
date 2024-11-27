package cnpm.ergo.service.implement;


import java.util.List;

import cnpm.ergo.service.interfaces.IProductService;
import cnpm.ergo.DAO.implement.*;
import cnpm.ergo.DAO.interfaces.*;
import cnpm.ergo.entity.Product;
public class ProductServiceImpl implements IProductService {

    private final IProductDao productDao = new ProductDaoImpl();

    @Override
    public void addProduct(Product product) {
        productDao.insert(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteProduct(int productId) {
        try {
            productDao.delete(productId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product with ID: " + productId);
        }
    }

    @Override
    public Product getProductById(int productId) {
        return productDao.findById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return productDao.searchByName(name);
    }

    @Override
    public int getProductCount() {
        return productDao.count();
    }
}

