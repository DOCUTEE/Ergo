package cnpm.ergo.service.interfaces;

import cnpm.ergo.entity.User;
import java.util.List;

public interface IUserService {
    // Create
    boolean addUser(User user);
    // Read
    List<User> getAllUsers();
    User getUserById(int userId);
    // Update
    boolean updateUser(User user);
    // Delete
    boolean deleteUser(int userId);
    boolean updateCustomerPassword(String email, String newPassword);// New method for updating password

    boolean getUserByEmail(String email);
}
