package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.Conversation;

public interface IConversationService {

	int getConversationCount();

	List<Conversation> getAllConversations();

	Conversation getConversationById(int conversationId);

	void deleteConversation(int conversationId);

	void updateConversation(Conversation conversation);

	void addConversation(Conversation conversation);

}
