package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.Review;

public interface IReviewService {
	List<Review> getReviewsAll(int productId);
}
