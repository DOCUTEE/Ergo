package cnpm.ergo.entity;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "question")
@NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionId")
    private int questionId;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
	private User user;
	@Column(name = "content", columnDefinition = "TEXT")
    private String content;
	@Column(name = "timestamp", columnDefinition = "DATETIME")
	private Date timestamp;
	@Column(name = "isPending", columnDefinition = "BIT")
	private boolean isPending;
}
