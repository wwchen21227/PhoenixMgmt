/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;

/**
 *
 * @author kooc
 */
@Action("deleteas")
public class DeleteAnnualScheduleCmd implements Perform {
        @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ScheduleDelegate del = this.getDelegate();
        String year = req.getParameter("year");
        del.processDeleteAS(Integer.parseInt(year));
        return "/pages/crudas.jsp";
    }  
    
    
    public ScheduleDelegate getDelegate(){
        return new ScheduleDelegate();
    }
}
