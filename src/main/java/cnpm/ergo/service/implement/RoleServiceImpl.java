package cnpm.ergo.service.implement;

import cnpm.ergo.DAO.implement.RoleDAOImpl;
import cnpm.ergo.DAO.interfaces.RoleDAO;
import cnpm.ergo.service.interfaces.RoleService;
import cnpm.ergo.entity.Role;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Override
    public boolean addRole(Role role) {
        RoleDAO roleDAO = new RoleDAOImpl();
        return roleDAO.addRole(role);
    }

    @Override
    public List<Role> getAllRoles() {
        RoleDAO roleDAO = new RoleDAOImpl();
        return roleDAO.getAllRoles();
    }

    @Override
    public Role getRoleById(int roleId)
    {
        RoleDAO roleDAO = new RoleDAOImpl();
        return roleDAO.getRoleById(roleId);
    }

    @Override
    public boolean updateRole(Role role) {
        RoleDAO roleDAO = new RoleDAOImpl();
        return roleDAO.updateRole(role);
    }

    @Override
    public boolean deleteRole(int roleId) {
        RoleDAO roleDAO = new RoleDAOImpl();
        return roleDAO.deleteRole(roleId);
    }

    public static void main(String[] args) {
        RoleService roleService = new RoleServiceImpl();
        Role role = new Role();
        role.setRoleName("Admin");
        roleService.addRole(role);

        List<Role> roles = roleService.getAllRoles();
        for (Role r : roles) {
            System.out.println(r.getRoleId() + " - " + r.getRoleName());
        }
    }

}
