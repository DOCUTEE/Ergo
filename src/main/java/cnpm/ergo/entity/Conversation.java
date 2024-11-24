package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "conversation")
@NamedQuery(name = "Conversation.findAll", query = "SELECT c FROM Conversation c")
public class Conversation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversationId")
    private int conversationId;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
	private List<User> users;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conversation")
    private List<Message> messages;
}
