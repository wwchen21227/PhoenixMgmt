/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.common;

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

/**
 *
 * @author aswathyl
 */
public class RadioProgramCommonCmd {
    
    private static final Log logger = LogFactory.getLog(RadioProgramCommonCmd.class);
    
     /**
     * Check whether the given path contains invalid escape sequences.
     * @param path the path to validate
     * @return {@code true} if the path is invalid, {@code false} otherwise
     */
    public static boolean isInvalidEncodedPath(String path) {
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
    public static boolean isInvalidPath(String path) {
            System.out.println("common cmd perform!!!! " + path);
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
                    if (relativePath.contains("url:")) {
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
