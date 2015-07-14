package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.groupsbean;
import dao.groupsdao;

/**
 * Servlet implementation class creategroupcontroller
 */
@WebServlet("/creategroupcontroller")
public class creategroupcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creategroupcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try{
			 String group_name=request.getParameter("gname");
	    	   groupsdao groupsd=new groupsdao();
	    	   groupsd.createGroup(group_name);
	    	   groupsdao groupsd2=new groupsdao();
	    	   groupsbean groups=groupsd2.getGroupbyName(group_name);
	    	   request.setAttribute("group", groups);
	    	   RequestDispatcher rd=request.getRequestDispatcher("mapuserstogroup.jsp");
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
