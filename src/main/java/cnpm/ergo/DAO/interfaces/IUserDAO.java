package cnpm.ergo.DAO.interfaces;

import cnpm.ergo.entity.User;
import java.util.List;

public interface IUserDAO {
    // Create
    boolean addUser(User user);
    // Read
    List<User> getAllUsers();
    User getUserById(int userId);
    // Update
    boolean updateUser(User user);
    // Delete
    boolean deleteUser(int userId);
    User getUserByEmail(String email);
}
