package cnpm.ergo.service.interfaces;

import cnpm.ergo.entity.Administrator;

public interface IAdministratorService {
    public boolean login(String email, String password);
    Administrator getAdministrator(String email);

}
