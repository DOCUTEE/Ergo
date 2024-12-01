package cnpm.ergo.service.implement;


import java.util.List;

import cnpm.ergo.service.interfaces.IProductImageService;
import cnpm.ergo.DAO.implement.*;
import cnpm.ergo.DAO.interfaces.*;
import cnpm.ergo.entity.ProductImage;
public class ProductImageServiceImpl implements IProductImageService {

    private static final IProductImage productImageDao = new ProductImageDaoImpl();

    @Override
    public void addProductImage(ProductImage productImage) {
        productImageDao.insert(productImage);
    }

    @Override
    public void deleteProductImage(ProductImage productImage) {
        try {
            productImageDao.delete(productImage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product image for Product ID: " + productImage.getProduct().getProductId());
        }
    }

    @Override
    public List<ProductImage> getProductImagesByProductId(int productId) {
        return productImageDao.findByProductId(productId);
    }

    @Override
    public List<ProductImage> getAllProductImages() {
        return productImageDao.findAll();
    }

    @Override
    public int getProductImageCount() {
        return productImageDao.count();
    }
}


