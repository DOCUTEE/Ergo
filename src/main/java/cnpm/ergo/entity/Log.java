package cnpm.ergo.entity;

import cnpm.ergo.DAO.implement.UserDAOImpl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "log")
@NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")

public class Log implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @Column(name = "dateLog")
    private LocalDateTime dateLog;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
        EntityManager em = emf.createEntityManager();

        // Start transaction
        em.getTransaction().begin();

        // Create new log
        Log log = new Log();
        log.setUser(new UserDAOImpl().getUserById(1));
        log.setDateLog(LocalDateTime.now());
        log.setContent("User login");

        // Persist log
        em.persist(log);

        // Commit transaction
        em.getTransaction().commit();

        // Close entity manager
        em.close();
    }
}
