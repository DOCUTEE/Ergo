package cnpm.ergo.service.implement;

import cnpm.ergo.DAO.implement.UserDAOImpl;
import cnpm.ergo.DAO.interfaces.IUserDAO;
import cnpm.ergo.entity.User;
import cnpm.ergo.service.interfaces.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDAO userDAO = new UserDAOImpl();

    // Create
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    // Read
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    // Update
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }


    @Override
    public boolean updateCustomerPassword(String email, String newPassword) {
        IUserDAO IUserDAO = new UserDAOImpl();

        // Assuming UserDAOImpl has a method to find user by email
        User user = IUserDAO.getUserByEmail(email);  // You should implement this in your DAO if not already available

        if (user != null) {
            user.setPassword(newPassword);  // Set the new password
            return IUserDAO.updateUser(user);  // Assuming this method updates the user in the database
        }

        return false;  // Return false if the user doesn't exist
    }

    @Override
    public boolean getUserByEmail(String email) {
        return false;
    }

    // Delete
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    public static void main(String[] args) {
        // Example usage
//        UserServiceImpl service = new UserServiceImpl();
//        boolean result = service.updateCustomerPassword("phucka004@gmail.com", "newPassword123");
//        System.out.println("Password updated: " + result);
    }
}
