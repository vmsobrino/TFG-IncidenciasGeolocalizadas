package edu.gimt.controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Logout Action
 */
public class LogoutAction extends ActionSupport {
	
	private static final long serialVersionUID = -5404993019124031949L;

	public String execute() {
		
//    	response.setContentType("text/html");
//    	Cookie[] cookies = request.getCookies();
//    	if (cookies != null) {
//	    	for (Cookie cookie : cookies) {
//	    		cookie.setMaxAge(0);
//	    		response.addCookie(cookie);
//	    	}
//    	}
//    	// invalidate sessions
//    	HttpSession session = request.getSession(false);
//    	if (session != null) {
//    		session.invalidate();
//    	}
//    	// no encoding because we have invalidated the session
//    	response.sendRedirect("./login.jsp");
		
		return SUCCESS;
    }

}
