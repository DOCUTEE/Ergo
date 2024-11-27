package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.Question;

public interface IQuestionService {

	int getQuestionCount();

	List<Question> getAllQuestions();

	Question getQuestionById(int questionId);

	void deleteQuestion(int questionId);

	void updateQuestion(Question question);

	void addQuestion(Question question);

}
