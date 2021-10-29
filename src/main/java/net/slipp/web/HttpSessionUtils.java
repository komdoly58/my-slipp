package net.slipp.web;

import javax.servlet.http.HttpSession;

import net.slipp.domain.User;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionUser";
	
	public static boolean isLoginUser(HttpSession session) {
		Object SessionUser = session.getAttribute(USER_SESSION_KEY);
		if(SessionUser==null)
			return false;
		return true;
	}
	public static User getUserFromSession(HttpSession session) {
		if(!isLoginUser(session))
		{
			return null;
		}
//		User SessionUser = (User) session.getAttribute(USER_SESSION_KEY);
		return (User) session.getAttribute(USER_SESSION_KEY);
	}
}
