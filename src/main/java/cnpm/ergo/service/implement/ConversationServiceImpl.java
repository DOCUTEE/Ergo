package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.DAO.implement.ConversationDAOImpl;
import cnpm.ergo.DAO.interfaces.IConversation;
import cnpm.ergo.entity.Conversation;
import cnpm.ergo.service.interfaces.IConversationService;

public class ConversationServiceImpl implements IConversationService{
	private final IConversation conversationDao = new ConversationDAOImpl();

    @Override
    public void addConversation(Conversation conversation) {
        conversationDao.insert(conversation);
    }

    @Override
    public void updateConversation(Conversation conversation) {
        conversationDao.update(conversation);
    }

    @Override
    public void deleteConversation(int conversationId) {
        try {
            conversationDao.delete(conversationId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting conversation with ID: " + conversationId);
        }
    }

    @Override
    public Conversation getConversationById(int conversationId) {
        return conversationDao.findById(conversationId);
    }

    @Override
    public List<Conversation> getAllConversations() {
        return conversationDao.findAll();
    }

    @Override
    public int getConversationCount() {
        return conversationDao.count();
    }
}
