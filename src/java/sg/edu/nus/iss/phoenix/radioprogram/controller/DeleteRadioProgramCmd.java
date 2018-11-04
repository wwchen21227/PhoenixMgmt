/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.common.RadioProgramCommonCmd;

/**
 *
 * @author boonkui
 */
@Action("deleterp")
public class DeleteRadioProgramCmd implements Perform {
    
   
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
            String name = req.getParameter("name");
            del.processDelete(name);

            ReviewSelectProgramDelegate rsDel =  this.getReviewSelectDelegate(); //new ReviewSelectProgramDelegate();
            List<RadioProgram> data = rsDel.reviewSelectRadioProgram();
            req.setAttribute("rps", data);
            return "/pages/crudrp.jsp";
        }
    }
    
    
    public ProgramDelegate getProgramDelegate(){
        return new ProgramDelegate();
    }
    
    public ReviewSelectProgramDelegate getReviewSelectDelegate() {
        return new ReviewSelectProgramDelegate();
    }
}
