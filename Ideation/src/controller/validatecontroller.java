package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.userbean;
import dao.userdao;

/**
 * Servlet implementation class validatecontroller
 */
@WebServlet("/validatecontroller")
public class validatecontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validatecontroller() {
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
		try
		{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String man1=request.getParameter("val");
			String test;
			if(man1.isEmpty())
				test="empty";
			else
			{
				userdao userd=new userdao();
				userbean user=userd.getUserbyId(man1);
				if(user==null)
				{
					test="invalid";
				}
				else
					test="valid";
			}
			out.print(test);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
