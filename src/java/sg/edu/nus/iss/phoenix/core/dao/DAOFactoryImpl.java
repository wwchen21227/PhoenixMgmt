package sg.edu.nus.iss.phoenix.core.dao;

import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.RoleDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDao;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDao;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDao;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDAOImpl;

public class DAOFactoryImpl implements DAOFactory {
	private UserDao userDAO = new UserDaoImpl();
	private RoleDao roleDAO = new RoleDaoImpl();
	private ProgramDAO rpdao = new ProgramDAOImpl();
        private AnnualScheduleDao asdao = new AnnualScheduleDAOImpl();
        private WeeklyScheduleDao wsdao = new WeeklyScheduleDAOImpl();
        private ProgramSlotDao psdao = new ProgramSlotDAOImpl();
        
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
	public ProgramSlotDao getProgramSlotDAO() {
		// TODO Auto-generated method stub
		return psdao;
	}
}
