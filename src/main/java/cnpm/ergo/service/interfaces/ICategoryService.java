package cnpm.ergo.service.interfaces;

import java.util.List;
import cnpm.ergo.entity.Category;

public interface ICategoryService {
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
    Category getCategoryById(int categoryId);
    List<Category> getAllCategories();
    List<Category> searchCategoriesByName(String name);
    int getCategoryCount();
	List<Category> getAllCategoriesName();
}
