/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.controller.LoginCmd;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.common.RadioProgramCommonCmd;

/**
 *
 * @author boonkui
 */
@Action("managerp")
public class ManageRadioProgramCmd implements Perform {
    
    private static final Log logger = LogFactory.getLog(ManageRadioProgramCmd.class);
    
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
            ReviewSelectProgramDelegate del = this.getRSPDelegate();
            List<RadioProgram> data = del.reviewSelectRadioProgram();
            req.setAttribute("rps", data);
            return "/pages/crudrp.jsp";
        }
    }
    
    public ReviewSelectProgramDelegate getRSPDelegate(){
        return new ReviewSelectProgramDelegate();
    }
}
