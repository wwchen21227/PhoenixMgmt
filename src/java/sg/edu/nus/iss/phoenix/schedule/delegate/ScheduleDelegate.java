/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.util.ArrayList;
import java.sql.Date;

import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author aswathyl
 */

public class ScheduleDelegate {
    
    	public ArrayList<AnnualSchedule> findAllAS() {
		ScheduleService service = new ScheduleService();
		return service.findAllAS();
		
	}
	
	public void processCreateAS(AnnualSchedule as) {
		ScheduleService service = new ScheduleService();
		service.processCreateAS(as);
		
	}
	public void processModifyAS(AnnualSchedule as) {
		ScheduleService service = new ScheduleService();
		service.processModifyAS(as);
		
	}

	public void processDeleteAS(Integer year) {
		ScheduleService service = new ScheduleService();
		service.processDeleteAS(year);
        }
        
        //=
        public ArrayList<WeeklySchedule> findAllWS() {
		ScheduleService service = new ScheduleService();
		return service.findAllWS();
		
	}
	
	public void processCreateWS(WeeklySchedule ws) {
		ScheduleService service = new ScheduleService();
		service.processCreateWS(ws);
		
	}
	public void processModifyWS(WeeklySchedule ws) {
		ScheduleService service = new ScheduleService();
		service.processModifyWS(ws);
		
	}

	public void processDeleteWS(Date startDate) {
		ScheduleService service = new ScheduleService();
		service.processDeleteWS(startDate);
        }
                
       public ArrayList<ProgramSlot> findAllPS() {
		ScheduleService service = new ScheduleService();
		return service.findAllPS();
		
	}
	
	public void processCreatePS(ProgramSlot ps) {
            System.out.println("here");
		ScheduleService service = new ScheduleService();
		service.processCreatePS(ps);
		
	}
	public void processModifyPS(ProgramSlot ps) {
		ScheduleService service = new ScheduleService();
		service.processModifyPS(ps);
		
	}
        
        public void processDeletePS(String programSlotId) {
		ScheduleService service = new ScheduleService();
		service.processDeletePS(programSlotId);
        }
}
