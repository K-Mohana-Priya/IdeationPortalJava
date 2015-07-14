package utility;

import net.jforum.context.RequestContext;
import net.jforum.entities.UserSession;
import net.jforum.sso.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Beans.userbean;
public class MyAppSSO implements SSO{


	public String authenticateUser(RequestContext request) {

	userbean user = (userbean)request.getSessionContext().getAttribute("user");

	return user.getUser_id();

	}

	public boolean isSessionValid(UserSession userSession, RequestContext request) {

	if(request.getSessionContext().getAttribute("user") != null)

	{

	return true;

	}else{

	return false;

	}

	}

	

	}

