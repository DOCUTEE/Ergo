package cnpm.ergo.entity;

import cnpm.ergo.DAO.implement.RoleDAOImpl;
import cnpm.ergo.DAO.interfaces.RoleDAO;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")


public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "phone", length = 100)
    private String phone;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "gender", length = 100)
    private String gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Log> logs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Role role;

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "isDelete", columnDefinition = "BIT")
    private Boolean isDelete;

    public static void main(String[] args) {
        RoleDAO roleDAO = new RoleDAOImpl();
        Role role = roleDAO.getRoleById(2);

        User user = new User();
        user.setName("Nguyen Van A");
        user.setEmail("A@gmail.com");
        user.setPassword("123456");
        user.setPhone("0123456789");
        user.setAddress("Ha Noi");
        user.setGender("Nam");
        user.setRole(role);
        user.setStatus("Active");
        user.setIsDelete(false);

    }
}



