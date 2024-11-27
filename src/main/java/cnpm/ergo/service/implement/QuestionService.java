package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.DAO.implement.QuestionDAOImpl;
import cnpm.ergo.DAO.interfaces.IQuestion;
import cnpm.ergo.entity.Question;
import cnpm.ergo.service.interfaces.IQuestionService;

public class QuestionService implements IQuestionService{
	private final IQuestion questionDao = new QuestionDAOImpl();

    @Override
    public void addQuestion(Question question) {
        questionDao.insert(question);
    }

    @Override
    public void updateQuestion(Question question) {
        questionDao.update(question);
    }

    @Override
    public void deleteQuestion(int questionId) {
        try {
            questionDao.delete(questionId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting question with ID: " + questionId);
        }
    }

    @Override
    public Question getQuestionById(int questionId) {
        return questionDao.findById(questionId);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    @Override
    public int getQuestionCount() {
        return questionDao.count();
    }
}
