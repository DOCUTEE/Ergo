package cnpm.ergo.service.implement;

import cnpm.ergo.DAO.implement.UserDAOImpl;
import cnpm.ergo.DAO.interfaces.UserDAO;
import cnpm.ergo.entity.User;
import cnpm.ergo.service.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    // Create
    public boolean addUser(User user) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.addUser(user);
    }

    // Read
    public List<User> getAllUsers() {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.getAllUsers();
    }

    public User getUserById(int userId) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.getUserById(userId);
    }

    // Update
    public boolean updateUser(User user) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.updateUser(user);
    }

    // Delete
    public boolean deleteUser(int userId) {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.deleteUser(userId);
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        userService.addUser(user);

        List<User> users = userService.getAllUsers();
        for (User u : users) {
            System.out.println(u.getUserId() + " - " + u.getName());
        }
    }
}
