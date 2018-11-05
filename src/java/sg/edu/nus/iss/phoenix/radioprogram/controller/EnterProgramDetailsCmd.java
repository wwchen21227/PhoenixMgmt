/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.radioprogram.common.RadioProgramCommonCmd;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 *
 * @author boonkui
 */
@Action("enterrp")
public class EnterProgramDetailsCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
         String requestUrl = req.getRequestURI() + '?' + req.getQueryString();
        
        if (RadioProgramCommonCmd.isInvalidPath(requestUrl)) {
            return "/pages/invalid.jsp";
	}
        else if (RadioProgramCommonCmd.isInvalidEncodedPath(requestUrl)) {
            return "/pages/invalid.jsp";
        }
        else {
            ProgramDelegate del = this.getProgramDelegate();
            RadioProgram rp = new RadioProgram();
            rp.setName(req.getParameter("name"));
            rp.setDescription(req.getParameter("description"));
            String dur = req.getParameter("typicalDuration");
            System.out.println(rp.toString());
            Time t = Time.valueOf(dur);
            rp.setTypicalDuration(t);
            String ins = (String) req.getParameter("ins");
            Logger.getLogger(getClass().getName()).log(Level.INFO,
                            "Insert Flag: " + ins);
            if (ins.equalsIgnoreCase("true")) {
                    del.processCreate(rp);
            } else {
                    del.processModify(rp);
            }

            ReviewSelectProgramDelegate rsdel = this.getRSPDelegate();
            List<RadioProgram> data = rsdel.reviewSelectRadioProgram();
            req.setAttribute("rps", data);
            return "/pages/crudrp.jsp";
        }
    }
    
    public ProgramDelegate getProgramDelegate(){
       return new ProgramDelegate();
    }
    
    public ReviewSelectProgramDelegate getRSPDelegate() {
        return new ReviewSelectProgramDelegate();
    }
}
