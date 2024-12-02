package cnpm.ergo.DAO.interfaces;

import java.util.List;
import cnpm.ergo.entity.Category;

public interface ICategoryDao {
    void insert(Category category);
    void update(Category category);
    void delete(int categoryId);
    Category findById(int categoryId);
    List<Category> findAll();
    List<Category> searchByName(String name);
    int count();
	List<Category> findAllCategoryName();
}
