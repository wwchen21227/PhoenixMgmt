/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDao;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDao;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author kooc
 */
public class ScheduleService {
    
    	DAOFactoryImpl factory;
	AnnualScheduleDao asdao;
	WeeklyScheduleDao wsdao;

	public ScheduleService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactoryImpl();
		asdao = factory.getAnnualScheduleDAO();
		wsdao = factory.getWeeklyScheduleDAO();

	}
    
}
