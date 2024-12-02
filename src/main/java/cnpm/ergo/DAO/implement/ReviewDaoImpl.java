package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IReviewDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Review;
import jakarta.persistence.EntityManager;

public class ReviewDaoImpl implements IReviewDao {

	@Override
	public List<Review> findReviewsAll(int productId) {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	        String jpql = "SELECT r FROM Review r WHERE r.product.productId = :productId";
	        return em.createQuery(jpql, Review.class)
	                .setParameter("productId", productId)
	                .getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        em.close();
	    }
	}

	 public static void main(String[] args) {
	        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
	        int productId = 1;

	        try {
	            List<Review> reviews = reviewDao.findReviewsAll(productId);
	            System.out.println("Reviews for Product ID: " + productId);
	            for (Review review : reviews) {
	                System.out.println("Review ID: " + review.getReviewId());
	                System.out.println("Content: " + review.getContent());
	                System.out.println("Rating: " + review.getRating());
	                System.out.println("Created At: " + review.getCreateAt());
	                System.out.println("----------------------------------");
	            }

	            if (reviews.isEmpty()) {
	                System.out.println("No reviews found for Product ID: " + productId);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println("Error retrieving reviews: " + e.getMessage());
	    }
	 }
}


