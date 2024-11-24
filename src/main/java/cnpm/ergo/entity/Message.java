package cnpm.ergo.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Message")
@NamedQuery(name = "Message.findAll", query = "SELECT c FROM Message c")
public class Message {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private int messageId;
	@Column(name = "content", columnDefinition = "TEXT")
    private String content;
	@Column(name = "timestamp", columnDefinition = "DATETIME")
	private Date timestamp;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User sender;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversationId", referencedColumnName = "conversationId")
	private Conversation conversation;
}
