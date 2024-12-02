package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.DAO.implement.ReviewDaoImpl;
import cnpm.ergo.DAO.interfaces.IReviewDao;
import cnpm.ergo.entity.Review;
import cnpm.ergo.service.interfaces.IReviewService;

public class ReviewServiceImpl implements IReviewService {
	private final IReviewDao reviewDao = new ReviewDaoImpl();

	@Override
    public List<Review> getReviewsAll(int productId) {
        return reviewDao.findReviewsAll(productId);
    }
}

