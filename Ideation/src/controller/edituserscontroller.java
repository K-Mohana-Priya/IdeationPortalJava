package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.user_rolebean;
import Beans.userbean;
import dao.user_roledao;
import dao.userdao;

/**
 * Servlet implementation class edituserscontroller
 */
@WebServlet("/edituserscontroller")
public class edituserscontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edituserscontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("val");
		try{
			userdao userd=new userdao();
			userbean user=userd.getUserbyId(id);
			request.setAttribute("euser", user);
			user_rolebean userrole=new user_roledao().getRolebyUId(id);
			int role=userrole.getRole_id();
	        RequestDispatcher rd;
			if(role==3)
			rd = request.getRequestDispatcher("edituser.jsp");
			else
		    rd = request.getRequestDispatcher("deleteadmin.jsp");	
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
