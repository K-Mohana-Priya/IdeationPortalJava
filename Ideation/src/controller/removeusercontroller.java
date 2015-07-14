package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.user_rolebean;
import dao.ideadao;
import dao.messagedao;
import dao.notificationsdao;
import dao.user_group_assodao;
import dao.user_roledao;
import dao.userdao;

/**
 * Servlet implementation class removeusercontroller
 */
@WebServlet("/removeusercontroller")
public class removeusercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeusercontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_id=request.getParameter("uid");
		 try{
			 user_rolebean userrole=new user_roledao().getRolebyUId(user_id);
				int role=userrole.getRole_id();
	    	   userdao userd=new userdao();
	    	   if(role==3)
	    	   userd.removeUserbyId(user_id);
	    	   else
	    		   userd.removeAdminbyId(user_id);   
	    	   ideadao idead=new ideadao();
	    	   idead.deleteIdeabyUId(user_id);
	    	   messagedao messaged=new messagedao();
	    	   messaged.deleteMessagebyUId(user_id);
	    	   notificationsdao notificationsd=new notificationsdao();
	    	   notificationsd.deleteNotificationsbyUId(user_id);
	    	   user_roledao user_roled=new user_roledao();
	    	   user_roled.deleteUserRolebyUId(user_id);
	    	   user_group_assodao user_group_assod=new user_group_assodao();
	    	   user_group_assod.deleteUserGroupbyUId(user_id);
	    	   RequestDispatcher rd;
	    	   if(role==3)
	    	   {
	    	   rd=request.getRequestDispatcher("removeusers.jsp");
	    	   }
	    	   else
	    	   {
	    		   rd=request.getRequestDispatcher("viewadmins.jsp");   
	    	   }
				rd.forward(request, response);
	       }
	       catch(Exception e){
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
