package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.groupsbean;
import dao.groupsdao;

/**
 * Servlet implementation class validategroupcontroller
 */
@WebServlet("/validategroupcontroller")
public class validategroupcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validategroupcontroller() {
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
			String gname=request.getParameter("val").trim();
			String test;
			if(gname.isEmpty())
				test="empty";
			else
			{
				groupsdao groupd=new groupsdao();
				groupsbean group=groupd.getGroupbyName(gname);
				if(group==null)
				{
					test="available";
				}
				else
					test="unavailable";
			}
			out.print(test);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
