package cnpm.ergo.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int roleId;

    @Column(name = "roleName", columnDefinition = "VARCHAR(100)")
    private String roleName;


    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
        EntityManager em = emf.createEntityManager();

        // Start transaction
        em.getTransaction().begin();

        // Create new role
        Role role = new Role();
        role.setRoleName("Admin");

        // Persist role
        em.persist(role);

        // Commit transaction
        em.getTransaction().commit();

        // Close entity manager
        em.close();
    }
}
