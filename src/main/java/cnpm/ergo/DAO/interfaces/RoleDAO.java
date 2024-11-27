package cnpm.ergo.DAO.interfaces;

import cnpm.ergo.entity.Role;
import java.util.List;

public interface RoleDAO {
    // Create
    boolean addRole(Role role);
    // Read
    List<Role> getAllRoles();
    Role getRoleById(int roleId);
    // Update
    boolean updateRole(Role role);
    // Delete
    boolean deleteRole(int roleId);

}
