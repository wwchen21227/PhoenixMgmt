/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;

/**
 *
 * @author kooc
 */
@Action("deleteas")
public class DeleteWeeklyScheduleCmd implements Perform {
        @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ScheduleDelegate del = new ScheduleDelegate();
        String startDate = req.getParameter("startDate");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        try{

            java.util.Date date = sdf1.parse(startDate);   
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
            del.processDeleteWS(sqlStartDate);
        
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "/pages/crudas.jsp";
    }
}
