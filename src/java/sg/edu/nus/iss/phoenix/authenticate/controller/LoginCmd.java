/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;


/**
 *
 * @author boonkui
 */
@Action("login")
public class LoginCmd implements Perform {
    
    AuthenticateDelegate delegate;
    private static final Log logger = LogFactory.getLog(LoginCmd.class);
    
    public LoginCmd(AuthenticateDelegate dele){
        super();
        this.delegate = dele;
    }
    
    
    public LoginCmd(){
        super();
    }
    

  //  @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AuthenticateDelegate ad;
        
        if(this.delegate == null) {
            ad = new AuthenticateDelegate();
        } else {
            ad = this.delegate;
        }
        String requestUrl = req.getRequestURL().toString();
        
        if (isInvalidPath(requestUrl)) {
            return "/pages/error.jsp";
	}
        else if (isInvalidEncodedPath(requestUrl)) {
            return "/pages/error.jsp";
        }
        else {
            User user = new User();
            user.setId(req.getParameter("id"));
            user.setPassword(req.getParameter("password"));
            user = ad.validateUserIdPassword(user);
            if (null != user) {
                req.getSession().setAttribute("user", user);
                return "/pages/home.jsp";
            } else
                return "/pages/error.jsp";
        }
    }
    
    
    /**
     * Check whether the given path contains invalid escape sequences.
     * @param path the path to validate
     * @return {@code true} if the path is invalid, {@code false} otherwise
     */
    private boolean isInvalidEncodedPath(String path) {
            if (path.contains("%")) {
                    try {
                        // Use URLDecoder (vs UriUtils) to preserve potentially decoded UTF-8 chars
                        String decodedPath = URLDecoder.decode(path, "UTF-8");
                        if (isInvalidPath(decodedPath)) {
                        return true;
                        }
                    }
                    catch (IllegalArgumentException | UnsupportedEncodingException ex) {
                            System.out.println(ex);
                    }
            }
            return false;
    }

    /**
     * Identifies invalid resource paths. By default rejects:
     * - Paths that contain "WEB-INF" or "META-INF"
     * - Paths that contain "../" 
     * - Paths that represent a url after the leading slash is removed.
     * @param path the path to validate
     * @return {@code true} if the path is invalid, {@code false} otherwise
     */
    protected boolean isInvalidPath(String path) {
            if (path.contains("WEB-INF") || path.contains("META-INF")) {
                    logger.warn("Path with \"WEB-INF\" or \"META-INF\": [" + path + "]");
                    return true;
            }
            if (path.contains("1=1") || path.contains("'1'='1'") || path.contains("1 = 1") || path.contains("'1' = '1'")) {
                    logger.warn("Invalid Path: contains always true condition ");
                    return true;
            }
            if (path.contains("#") || path.contains("--")) {
                    logger.warn("Invalid Path: contains syntax to introduce comments ");
                    return true;
            }
            if (path.contains(":/")) {
                    String relativePath = (path.charAt(0) == '/' ? path.substring(1) : path);
                    if (relativePath.startsWith("url:")) {
                            logger.warn("Path represents URL or has \"url:\" prefix: [" + path + "]");
                            return true;
                    }
            }
            if (path.contains("..")) {
                    //path = StringUtils.cleanPath(path);
                    if (path.contains("../")) {
                            logger.warn("Invalid Path: contains \"../\".");
                            return true;
                    }
            }
            return false;
    }
}
