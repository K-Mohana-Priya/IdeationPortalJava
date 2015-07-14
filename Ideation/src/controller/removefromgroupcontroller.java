package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.groupsbean;
import dao.groupsdao;
import dao.user_group_assodao;

/**
 * Servlet implementation class removefromgroupcontroller
 */
@WebServlet("/removefromgroupcontroller")
public class removefromgroupcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removefromgroupcontroller() {
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
			 String group_name=request.getParameter("gname");
			 int group_id=Integer.parseInt(request.getParameter("gid"));
	    	   user_group_assodao ugroup=new user_group_assodao();
	    	   ugroup.removeUserFromGroup(user_id, group_id);
	    	   
	       }
	       catch(Exception e){
	     	  e.printStackTrace(); 
	        }
	}

}
