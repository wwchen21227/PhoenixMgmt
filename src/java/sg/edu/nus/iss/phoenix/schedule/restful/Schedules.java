/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author kooc
 */
public class Schedules {
    
    private List <AnnualSchedule> asList;
    private List <WeeklySchedule> wsList;
    private List <ProgramSlot> psList;

    public List<AnnualSchedule> getAsList() {
        return asList;
    }
 
    public void setAsList(List<AnnualSchedule> asList) {
        this.asList = asList;
    }

    public List<WeeklySchedule> getWsList() {
        return wsList;
    }
 
    public void setWsList(List<WeeklySchedule> wsList) {
        this.wsList = wsList;
    }
    
    public List<ProgramSlot> getPsList() {
        return psList;
    }
 
    public void setPsList(List<ProgramSlot> psList) {
        this.psList = psList;
    }
}
