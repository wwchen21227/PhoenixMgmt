/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDao;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDao;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDao;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author kooc
 */
public class ScheduleService {
    
    	DAOFactoryImpl factory;
	AnnualScheduleDao asdao;
	WeeklyScheduleDao wsdao;
        ProgramSlotDao psdao;

	public ScheduleService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactoryImpl();
		asdao = factory.getAnnualScheduleDAO();
		wsdao = factory.getWeeklyScheduleDAO();
                psdao = factory.getProgramSlotDAO();
        }

        public ArrayList<AnnualSchedule> searchAnnualSchedules(AnnualSchedule asso) {
		ArrayList<AnnualSchedule> list = new ArrayList<AnnualSchedule>();
		try {
			list = (ArrayList<AnnualSchedule>) asdao.searchMatching(asso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<AnnualSchedule> findASByCriteria(AnnualSchedule as) {
		ArrayList<AnnualSchedule> currentList = new ArrayList<AnnualSchedule>();

		try {
			currentList = (ArrayList<AnnualSchedule>) asdao.searchMatching(as);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return currentList;

	}

	public AnnualSchedule findAS(Integer asYear) {
		AnnualSchedule currentas = new AnnualSchedule();
		currentas.setYear(asYear);
		try {
			currentas = ((ArrayList<AnnualSchedule>) asdao
					.searchMatching(currentas)).get(0);
			return currentas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentas;

	}

	public ArrayList<AnnualSchedule> findAllAS() {
		ArrayList<AnnualSchedule> currentList = new ArrayList<AnnualSchedule>();
		try {
			currentList = (ArrayList<AnnualSchedule>) asdao.loadAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentList;

	}
        
	public void processCreateAS(AnnualSchedule as) {
		try {
			asdao.create(as);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processModifyAS(AnnualSchedule as) {
		
			try {
				asdao.save(as);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void processDeleteAS(Integer year) {

            try {
                AnnualSchedule as = new AnnualSchedule(year);
                asdao.delete(as);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}
        
        public ArrayList<WeeklySchedule> searchWeeklySchedule(WeeklySchedule wsso) {
		ArrayList<WeeklySchedule> list = new ArrayList<WeeklySchedule>();
		try {
			list = (ArrayList<WeeklySchedule>) wsdao.searchMatching(wsso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<WeeklySchedule> findWSByCriteria(WeeklySchedule ws) {
		ArrayList<WeeklySchedule> currentList = new ArrayList<WeeklySchedule>();

		try {
			currentList = (ArrayList<WeeklySchedule>) wsdao.searchMatching(ws);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return currentList;

	}

	public WeeklySchedule findWSbyDate(Date wsStartDate) {
		WeeklySchedule currentws = new WeeklySchedule();
		currentws.setStartDate(wsStartDate);
		try {
			currentws = ((ArrayList<WeeklySchedule>) wsdao
					.searchMatching(currentws)).get(0);
			return currentws;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentws;

	}

	public ArrayList<WeeklySchedule> findAllWS() {
		ArrayList<WeeklySchedule> currentList = new ArrayList<WeeklySchedule>();
		try {
			currentList = (ArrayList<WeeklySchedule>) wsdao.loadAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentList;

	}
        
	public void processCreateWS(WeeklySchedule ws) {
		try {
			wsdao.create(ws);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processModifyWS(WeeklySchedule ws) {
		
			try {
				wsdao.save(ws);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void processDeleteWS(Date startDate) {

            try {
                WeeklySchedule ws = new WeeklySchedule(startDate);
                wsdao.delete(ws);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}
    
        public ArrayList<ProgramSlot> searchProgramSlot(ProgramSlot psso) {
		ArrayList<ProgramSlot> list = new ArrayList<ProgramSlot>();
		try {
			list = (ArrayList<ProgramSlot>) psdao.searchMatching(psso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ProgramSlot> findPSByCriteria(ProgramSlot ps) {
		ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();

		try {
			currentList = (ArrayList<ProgramSlot>) psdao.searchMatching(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return currentList;

	}

	public ProgramSlot findPSByDate(Date psDateOfProgram) {
            ProgramSlot currentps = new ProgramSlot();
            currentps.setDateOfProgram(psDateOfProgram);
            try {
                    currentps = ((ArrayList<ProgramSlot>) psdao
                                    .searchMatching(currentps)).get(0);
                    return currentps;
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            return currentps;

	}

	public ArrayList<ProgramSlot> findAllPS() {
            ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
            try {
                    currentList = (ArrayList<ProgramSlot>) psdao.loadAll();
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            return currentList;

	}
        
	public void processCreatePS(ProgramSlot ps) {
            try {
                    psdao.create(ps);
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
	}

	public void processModifyPS(ProgramSlot ps) {
		
            try {
                   psdao.save(ps);
            } catch (NotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
		
	}

	public void processDeletePS(String programSlotId) {

            try {
                ProgramSlot ps = new ProgramSlot(programSlotId);
                psdao.delete(ps);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}
        
        public String checkOverLap() {
            String count="0";
            try {
                  //count=  psdao.checkOverLap();
                  count= String.valueOf( psdao.checkTimeOverLap());
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            return count;
	}
}
