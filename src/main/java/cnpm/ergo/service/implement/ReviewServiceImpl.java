package cnpm.ergo.service.implement;

import cnpm.ergo.DAO.implement.ReviewDaoImpl;
import cnpm.ergo.DAO.interfaces.IReviewDao;
import cnpm.ergo.entity.Review;
import cnpm.ergo.service.interfaces.IReviewService;

public class ReviewServiceImpl implements IReviewService {
	private final IReviewDao reviewDao = new ReviewDaoImpl();

	@Override
	public void addReview(Review review) {
		reviewDao.insertReview(review);
	}

}
