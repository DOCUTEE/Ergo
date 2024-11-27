package cnpm.ergo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
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
    @Column(name = "messsageId")
    private int messageId;
	@Column(name = "content", columnDefinition = "TEXT")
    private String content;
	@Column(name = "timestamp", columnDefinition = "DATETIME")
	private Date timestamp;
	@Column(name = "senderId")
	private int senderId;
	@Column(name = "receiverId")
	private int receiverId;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversationId", referencedColumnName = "conversationId")
	private int conversationId;
}
