package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.DatabaseUtility;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class userdisplaycontroller
 */
@WebServlet("/userdisplaycontroller")
public class userdisplaycontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userdisplaycontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			Connection connection;
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			String  s=request.getParameter("val");
			connection = DatabaseUtility.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("select distinct user_id from user where user_id in (select user_id from user_role where role_id=3) and user_id like '"+s+"%' and valid=true order by user_id asc");
			resultSet = preparedStatement.executeQuery();
			int i=0;
			while(resultSet.next())
			{
				if(i<50)
					out.print("<span class='userlink' onclick='servletreq(this)' onmouseover='changestyle1(this)' onmouseout='changestyle2(this)'>"+resultSet.getString(1)+"</span><br/>");
				else
					break;
				i++;
		    }
			if(i==0)
			{
				out.print("<span>No Suggestions found</span><br/>");
			}
			DatabaseUtility.closeConnection(connection);
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
		try{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			Connection connection;
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			connection = DatabaseUtility.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("select * from user where valid=true and user_id in (select user_id from user_role where role_id=3)");
			resultSet = preparedStatement.executeQuery();
			int i=0;
			while(resultSet.next())
			{
				if(i<50)
					out.print("<span class='userlink' onclick='servletreq(this)' onmouseover='changestyle1(this)' onmouseout='changestyle2(this)'>"+resultSet.getString(1)+"</span><br/>");
				else
					break;
				i++;
		    }
			if(i==0)
			{
				out.print("<span>No Suggestions found</span><br/>");
			}
			DatabaseUtility.closeConnection(connection);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
