package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.Message;

public interface IMessage {

	int count();

	List<Message> findAll();

	Message findById(int messageId);

	void delete(int messageId);

	void update(Message message);

	void insert(Message message);

}
