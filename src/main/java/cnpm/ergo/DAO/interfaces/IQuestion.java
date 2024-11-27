package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.Question;

public interface IQuestion {

	int count();

	List<Question> findAll();

	Question findById(int questionId);

	void delete(int questionId);

	void update(Question question);

	void insert(Question question);
	
}
