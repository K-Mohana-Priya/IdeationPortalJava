package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Beans.user_rolebean;
import dao.user_roledao;
import dao.userdao;

/**
 * Servlet implementation class editusercontroller
 */
@WebServlet("/editusercontroller")
public class editusercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editusercontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
		String user_id=request.getParameter("uid");
		user_rolebean userrole=new user_roledao().getRolebyUId(user_id);
		int role=userrole.getRole_id();
		String user_name=request.getParameter("uname");
        String email=request.getParameter("uemail");
       String mobile_no=request.getParameter("umobile");
       int level=Integer.parseInt(request.getParameter("ulevel"));
       int layer=Integer.parseInt(request.getParameter("ulayer"));
       String manager_id_1="",manager_id_2="";
       if(role==3)
       {
        manager_id_1=request.getParameter("man1");
        manager_id_2=request.getParameter("man2");
       }
       String doj=request.getParameter("udoj");
       
    	   java.sql.Date date_joined = java.sql.Date.valueOf(doj);
    	   userdao userd=new userdao();
    	   RequestDispatcher rd;
    	   if(role==3)
    	   {
    	   userd.updateUser(user_id, user_name, email, date_joined, level, layer, mobile_no, manager_id_1, manager_id_2);
    	   rd=request.getRequestDispatcher("editusers.jsp");
    	   }
    	   else
    	   {
    		   userd.updateAdmin(user_id, user_name, email, date_joined, level, layer, mobile_no);
        	   rd=request.getRequestDispatcher("viewadmins.jsp");   
    	   }
			rd.forward(request, response);
       }
       catch(Exception e){
     	  e.printStackTrace(); 
        }
	}

}
