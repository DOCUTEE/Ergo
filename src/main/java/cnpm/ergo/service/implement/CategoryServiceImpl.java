package cnpm.ergo.service.implement;

import java.util.List;
import cnpm.ergo.service.interfaces.ICategoryService;
import cnpm.ergo.DAO.implement.CategoryDaoImpl;
import cnpm.ergo.DAO.interfaces.ICategoryDao;
import cnpm.ergo.entity.Category;

public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void addCategory(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.update(category);
    }
    @Override
    public List<Category> getAllCategoriesName() {
        return categoryDao.findAllCategoryName();
    }
    @Override
    public void deleteCategory(int categoryId) {
        try {
            categoryDao.delete(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting category with ID: " + categoryId);
        }
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryDao.findById(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> searchCategoriesByName(String name) {
        return categoryDao.searchByName(name);
    }

    @Override
    public int getCategoryCount() {
        return categoryDao.count();
    }
}
