package sg.edu.nus.iss.phoenix.core.dao;

import sg.edu.nus.iss.phoenix.authenticate.dao.PresentorDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.ProducerDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.RoleDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDao;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDao;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.PresentorDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.ProducerDaoImpl;

public class DAOFactoryImpl implements DAOFactory {

    private final UserDao userDAO = new UserDaoImpl();
    private final RoleDao roleDAO = new RoleDaoImpl();
    private final ProgramDAO rpdao = new ProgramDAOImpl();
    private final AnnualScheduleDao asdao = new AnnualScheduleDAOImpl();
    private final WeeklyScheduleDao wsdao = new WeeklyScheduleDAOImpl();
    private final PresentorDao predao = new PresentorDaoImpl();
    private final ProducerDao prodao = new ProducerDaoImpl();

    @Override
    public UserDao getUserDAO() {
        // TODO Auto-generated method stub
        return userDAO;
    }

    @Override
    public RoleDao getRoleDAO() {
        // TODO Auto-generated method stub
        return roleDAO;
    }

    @Override
    public ProgramDAO getProgramDAO() {
        // TODO Auto-generated method stub
        return rpdao;
    }

    @Override
    public AnnualScheduleDao getAnnualScheduleDAO() {
        // TODO Auto-generated method stub
        return asdao;
    }

    @Override
    public WeeklyScheduleDao getWeeklyScheduleDAO() {
        // TODO Auto-generated method stub
        return wsdao;
    }

    @Override
    public ProducerDao getProducerDao() {
        return prodao;
    }

    @Override
    public PresentorDao getPresenterDao() {
        return predao; //To change body of generated methods, choose Tools | Templates.
    }
}
