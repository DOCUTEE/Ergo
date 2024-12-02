package cnpm.ergo.entity;

import cnpm.ergo.DAO.implement.CategoryDaoImpl;
import cnpm.ergo.DAO.implement.ProductDaoImpl;
import cnpm.ergo.DAO.interfaces.ICategoryDao;
import cnpm.ergo.DAO.interfaces.IProductDao;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private int categoryId;

    @Column(name = "categoryName", columnDefinition = "NVARCHAR(200) NOT NULL")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    public String getName() {
        return categoryName;
    }
    public static void main(String[] args) {
        //insert category
        ICategoryDao categoryDao = new CategoryDaoImpl();
        Category category = new Category();
        category.setCategoryName("categoryName");
        categoryDao.insert(category);

        //insert product with category
        Product product = new Product();
        product.setName("productName");
        product.setDescript("descript");
        product.setDelete(false);
        product.setCategory(category);

        //insert product
        IProductDao productDao = new ProductDaoImpl();
        productDao.insert(product);
    }


}
