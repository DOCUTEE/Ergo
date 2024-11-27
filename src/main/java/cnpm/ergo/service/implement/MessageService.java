package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.DAO.implement.MessageDAOImpl;
import cnpm.ergo.DAO.interfaces.IMessage;
import cnpm.ergo.entity.Message;
import cnpm.ergo.service.interfaces.IMessageService;

public class MessageService implements IMessageService{
	private final IMessage messageDao = new MessageDAOImpl();

    @Override
    public void addMessage(Message message) {
        messageDao.insert(message);
    }

    @Override
    public void updateMessage(Message message) {
        messageDao.update(message);
    }

    @Override
    public void deleteMessage(int messageId) {
        try {
            messageDao.delete(messageId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting message with ID: " + messageId);
        }
    }

    @Override
    public Message getMessageById(int messageId) {
        return messageDao.findById(messageId);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageDao.findAll();
    }

    @Override
    public int getMessageCount() {
        return messageDao.count();
    }
}
