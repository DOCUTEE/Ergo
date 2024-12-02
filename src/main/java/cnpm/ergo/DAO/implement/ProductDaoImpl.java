package cnpm.ergo.DAO.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import cnpm.ergo.DAO.implement.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;

import cnpm.ergo.DAO.interfaces.IProductDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Product;

public class ProductDaoImpl implements IProductDao {

	@Override
	public void insert(Product product) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(product);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Product product) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.merge(product);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int productId) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			Product product = em.find(Product.class, productId);
			if (product != null) {
				em.remove(product);
			}
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public Product findById(int productId) {
		EntityManager em = JPAConfig.getEntityManager();
		return em.find(Product.class, productId);
	}

	@Override
	public List<Product> findAllList(int page, int size) {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	        String jpql = "SELECT p FROM Product p "
	                    + "LEFT JOIN p.productImages "
	                    + "LEFT JOIN p.productTypes pt "
	                    + "LEFT JOIN p.category "
	                    + "WHERE p.isDelete = false";
	        List<Product> products = em.createQuery(jpql, Product.class)
	                                   .setFirstResult((page - 1) * size)
	                                   .setMaxResults(size)
	                                   .getResultList();

	        return products;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        em.close();
	    }
	}


	@Override
	public int count() {
		EntityManager em = JPAConfig.getEntityManager();
		String jpql = "SELECT COUNT(p) FROM Product p";
		Query query = em.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public int count(String categoryId) {
		EntityManager em = JPAConfig.getEntityManager();
		String jpql = "SELECT COUNT(p) FROM Product p WHERE p.isDelete = false";

		if (categoryId != null && !categoryId.isEmpty()) {
			jpql += " AND p.category.categoryId = :categoryId";
		}

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		if (categoryId != null && !categoryId.isEmpty()) {
			query.setParameter("categoryId", Integer.parseInt(categoryId));
		}

		return query.getSingleResult().intValue();
	}

	@Override
	public List<String> findAllColors() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT DISTINCT pt.color FROM ProductType pt WHERE pt.color IS NOT NULL";
			return em.createQuery(jpql, String.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Double> findAllLengths() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT DISTINCT pt.length FROM ProductType pt WHERE pt.length IS NOT NULL";
			return em.createQuery(jpql, Double.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Double> findAllHeights() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT DISTINCT pt.height FROM ProductType pt WHERE pt.height IS NOT NULL";
			return em.createQuery(jpql, Double.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<String> findAllMaterials() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT DISTINCT pt.material FROM ProductType pt WHERE pt.material IS NOT NULL";
			return em.createQuery(jpql, String.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> findByKeywordOrCategory(String keyword, String categoryName, int page, int size) {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	        StringBuilder jpql = new StringBuilder("SELECT DISTINCT p FROM Product p ");
	        jpql.append("LEFT JOIN FETCH p.productTypes pt ");  // Chỉ tải productTypes trong truy vấn này
	        jpql.append("LEFT JOIN p.category c ");
	        jpql.append("WHERE p.isDelete = false ");

	        if (categoryName != null && !categoryName.isEmpty()) {
	            jpql.append("AND c.categoryName LIKE :categoryName ");
	        }
	        if (keyword != null && !keyword.isEmpty()) {
	            jpql.append("AND (p.name LIKE :keyword OR p.descript LIKE :keyword) ");
	        }

	        TypedQuery<Product> query = em.createQuery(jpql.toString(), Product.class);
	        if (categoryName != null && !categoryName.isEmpty()) {
	            query.setParameter("categoryName", "%" + categoryName + "%");
	        }
	        if (keyword != null && !keyword.isEmpty()) {
	            query.setParameter("keyword", "%" + keyword + "%");
	        }

	        query.setFirstResult((page - 1) * size);
	        query.setMaxResults(size);

	        List<Product> products = query.getResultList();

	        for (Product product : products) {
	            Hibernate.initialize(product.getProductImages());
	        }

	        return products;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        em.close();
	    }
	}
	@Override
	public List<Product> applyFiltersAfterKeywordOrCategory(List<Long> productIdsLong, String filterPrice,
	                                                          String[] colors, String[] materials, String[] heights, String[] lengths,
	                                                          int page, int size) {
	    if (productIdsLong == null || productIdsLong.isEmpty()) {
	        return List.of();
	    }

	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	        StringBuilder jpql = new StringBuilder("SELECT DISTINCT p FROM Product p ");

	        jpql.append("LEFT JOIN FETCH p.productTypes pt ");
	        jpql.append("LEFT JOIN p.category c ");
	        jpql.append("WHERE p.productId IN :productIds ");

	        // Apply filter conditions
	        if (filterPrice != null && !filterPrice.isEmpty()) {
	            jpql.append("AND pt.price BETWEEN :minPrice AND :maxPrice ");
	        }
	        if (colors != null && colors.length > 0) {
	            jpql.append("AND pt.color IN :colors ");
	        }
	        if (materials != null && materials.length > 0) {
	            jpql.append("AND pt.material IN :materials ");
	        }
	        if (heights != null && heights.length > 0) {
	            jpql.append("AND pt.height IN :heights ");
	        }
	        if (lengths != null && lengths.length > 0) {
	            jpql.append("AND pt.length IN :lengths ");
	        }

	        TypedQuery<Product> query = em.createQuery(jpql.toString(), Product.class);
	        query.setParameter("productIds", productIdsLong);

	        if (filterPrice != null && !filterPrice.isEmpty()) {
	            String[] prices = filterPrice.split("-");
	            query.setParameter("minPrice", Double.parseDouble(prices[0]));
	            query.setParameter("maxPrice", Double.parseDouble(prices[1]));
	        }

	        if (colors != null && colors.length > 0) {
	            query.setParameter("colors", List.of(colors));
	        }
	        if (materials != null && materials.length > 0) {
	            query.setParameter("materials", List.of(materials));
	        }
	        if (heights != null && heights.length > 0) {
	            query.setParameter("heights", List.of(heights));
	        }
	        if (lengths != null && lengths.length > 0) {
	            query.setParameter("lengths", List.of(lengths));
	        }

	        query.setFirstResult((page - 1) * size);
	        query.setMaxResults(size);

	        List<Product> products = query.getResultList();

	        for (Product product : products) {
	            Hibernate.initialize(product.getProductImages());
	        }

	        return products;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        em.close();
	    }
	}
	@Override
	public long Count(String keyword, String categoryName, String filterPrice,
	                            String[] colors, String[] materials, String[] heights, String[] lengths) {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	        StringBuilder jpql = new StringBuilder("SELECT COUNT(DISTINCT p) FROM Product p ");
	        jpql.append("LEFT JOIN p.productTypes pt ");
	        jpql.append("LEFT JOIN p.category c ");
	        jpql.append("WHERE p.isDelete = false ");
	        if (categoryName != null && !categoryName.isEmpty()) {
	            jpql.append("AND c.categoryName LIKE :categoryName ");
	        }
	        if (keyword != null && !keyword.isEmpty()) {
	            jpql.append("AND (p.name LIKE :keyword OR p.descript LIKE :keyword) ");
	        }
	        if (filterPrice != null && !filterPrice.isEmpty()) {
	            jpql.append("AND pt.price BETWEEN :minPrice AND :maxPrice ");
	        }

	        if (colors != null && colors.length > 0) {
	            jpql.append("AND pt.color IN :colors ");
	        }
	        if (materials != null && materials.length > 0) {
	            jpql.append("AND pt.material IN :materials ");
	        }
	        if (heights != null && heights.length > 0) {
	            jpql.append("AND pt.height IN :heights ");
	        }
	        if (lengths != null && lengths.length > 0) {
	            jpql.append("AND pt.length IN :lengths ");
	        }

	        TypedQuery<Long> query = em.createQuery(jpql.toString(), Long.class);
	        if (categoryName != null && !categoryName.isEmpty()) {
	            query.setParameter("categoryName", "%" + categoryName + "%");
	        }
	        if (keyword != null && !keyword.isEmpty()) {
	            query.setParameter("keyword", "%" + keyword + "%");
	        }
	        if (filterPrice != null && !filterPrice.isEmpty()) {
	            try {
	                String[] prices = filterPrice.split("-");
	                query.setParameter("minPrice", Double.parseDouble(prices[0]));
	                query.setParameter("maxPrice", Double.parseDouble(prices[1]));
	            } catch (NumberFormatException e) {
	                throw new IllegalArgumentException("Invalid price range format: " + filterPrice, e);
	            }
	        }
	        if (colors != null && colors.length > 0) {
	            query.setParameter("colors", List.of(colors));
	        }
	        if (materials != null && materials.length > 0) {
	            query.setParameter("materials", List.of(materials));
	        }
	        if (heights != null && heights.length > 0) {
	            query.setParameter("heights", List.of(heights));
	        }
	        if (lengths != null && lengths.length > 0) {
	            query.setParameter("lengths", List.of(lengths));
	        }
	        return query.getSingleResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        em.close();
	    }
	}
	@Override
    public List<Product> findRelatedProductsByProductId(int productId, int page, int pageSize) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            Product currentProduct = em.find(Product.class, productId);
            if (currentProduct == null) {
                throw new IllegalArgumentException("Sản phẩm không tồn tại.");
            }

            String categoryName = currentProduct.getCategory().getCategoryName();

            StringBuilder jpql = new StringBuilder("SELECT DISTINCT p FROM Product p ");
            jpql.append("LEFT JOIN FETCH p.productTypes pt ");
            jpql.append("LEFT JOIN p.category c ");
            jpql.append("WHERE p.isDelete = false ");
            jpql.append("AND c.categoryName = :categoryName ");
            jpql.append("AND p.productId != :productId ");
            jpql.append("ORDER BY p.productId");

            TypedQuery<Product> query = em.createQuery(jpql.toString(), Product.class);
            query.setParameter("categoryName", categoryName);
            query.setParameter("productId", productId);

            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);

            List<Product> relatedProducts = query.getResultList();

            for (Product product : relatedProducts) {
                Hibernate.initialize(product.getProductTypes());
                Hibernate.initialize(product.getProductImages());
            }

            return relatedProducts;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public long getTotalRelatedProducts(int productId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            Product currentProduct = em.find(Product.class, productId);
            if (currentProduct == null) {
                throw new IllegalArgumentException("Sản phẩm không tồn tại.");
            }

            String categoryName = currentProduct.getCategory().getCategoryName();
            StringBuilder jpql = new StringBuilder("SELECT COUNT(DISTINCT p) FROM Product p ");
            jpql.append("LEFT JOIN p.category c ");
            jpql.append("WHERE p.isDelete = false ");
            jpql.append("AND c.categoryName = :categoryName ");
            jpql.append("AND p.productId != :productId");

            TypedQuery<Long> query = em.createQuery(jpql.toString(), Long.class);
            query.setParameter("categoryName", categoryName);
            query.setParameter("productId", productId);

            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        ProductDaoImpl productRepository = new ProductDaoImpl();
        int page = 2; // Trang bắt đầu
        int size = 10; // Số lượng sản phẩm mỗi trang

        // Thử nghiệm với từ khóa và tên danh mục
        String keyword = ""; // Thay bằng từ khóa thực tế
        String categoryName = ""; // Thay bằng tên danh mục thực tế

        try {
            List<Product> products = productRepository.findByKeywordOrCategory(keyword, categoryName, page, size);

            System.out.println("Kết quả tìm kiếm:");
            for (Product product : products) {
                System.out.println("Tên sản phẩm: " + product.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Có lỗi xảy ra trong quá trình truy vấn!");
        }
    }

}
