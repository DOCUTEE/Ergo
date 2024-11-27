package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.Message;

public interface IMessageService {

	int getMessageCount();

	List<Message> getAllMessages();

	Message getMessageById(int messageId);

	void deleteMessage(int messageId);

	void updateMessage(Message message);

	void addMessage(Message message);

}
