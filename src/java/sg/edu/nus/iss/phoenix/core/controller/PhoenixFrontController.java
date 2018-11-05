package sg.edu.nus.iss.phoenix.core.controller;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PhoenixFrontController
 */
public class PhoenixFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private static final Log logger = LogFactory.getLog(PhoenixFrontController.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhoenixFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * Process requests from clients.
	 */
	protected void processRequest(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		String action = FCUtilities.stripPath(pathInfo);
                String requestUrl =  request.getRequestURI() + '?' + request.getQueryString();
                String result;
		System.out.println("PATH" + pathInfo);
		System.out.println("ACTION" + action);
		if (isInvalidPath(requestUrl)) {
                    result = chooseUseCase("error");
		}
                else if (isInvalidEncodedPath(requestUrl)) {
                    result = chooseUseCase("error");
		}
		else {
                    result = chooseUseCase(action);
		}
                RequestDispatcher rd = getServletContext().getRequestDispatcher(result);
		rd.forward(request, response);
	}

	private String chooseUseCase(String action) {
		switch (action) {
		case "login":
			return "/LoginController/login";
		case "searchrp":
			return "/ProcessController/search";
		case "setuprp":
			return "/ProcessController/process";
                case "loadrp":
			return "/ProcessController/load";	
		case "deleterp":
			return "/ProcessController/delete";
		case "crudrp":
			return "/CRUDRpController";
                case "searchps":
			return "/ProcessController/search";
		case "setupps":
			return "/ProcessController/process";
		case "crudps":
			return "/CRUDPsController";
		case "loadps":
			return "/ProcessController/load";	
		case "deleteps":
			return "/ProcessController/delete";
		case "logout":
			return "/LoginController/logout";
                case "error":
                        return "/invalid.jsp";
		default:
			return "/welcome.jsp";
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
