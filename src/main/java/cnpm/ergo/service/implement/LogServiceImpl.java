package cnpm.ergo.service.implement;

import cnpm.ergo.DAO.implement.LogDAOImpl;
import cnpm.ergo.DAO.implement.UserDAOImpl;
import cnpm.ergo.DAO.interfaces.LogDAO;
import cnpm.ergo.entity.Log;
import cnpm.ergo.service.interfaces.LogService;

public class LogServiceImpl implements LogService {
    @Override
    public boolean addLog(Log log) {
        LogDAO logDAO = new LogDAOImpl();
        return logDAO.addLog(log);
    }

    @Override
    public Log getLogById(int logId) {
        LogDAO logDAO = new LogDAOImpl();
        return logDAO.getLogById(logId);
    }

    @Override
    public boolean updateLog(Log log) {
        LogDAO logDAO = new LogDAOImpl();
        return logDAO.updateLog(log);
    }

    @Override
    public boolean deleteLog(int logId) {
        return false;
    }

    public static void main(String[] args) {
        LogService logService = new LogServiceImpl();
        Log log = new Log();
        log.setContent("User login");
        log.setDateLog(java.time.LocalDateTime.now());
        log.setUser(new UserDAOImpl().getUserById(2));
        logService.addLog(log);

    }

}
