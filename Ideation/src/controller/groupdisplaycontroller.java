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
 * Servlet implementation class groupdisplaycontroller
 */
@WebServlet("/groupdisplaycontroller")
public class groupdisplaycontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public groupdisplaycontroller() {
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
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			Connection connection;
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			connection = DatabaseUtility.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("select * from groups");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
					out.print("<tr onclick='servletreq(this)' onmouseover='changestyle1(this)' onmouseout='changestyle2(this)'><td>"+resultSet.getInt(1)+"</td><td>"+resultSet.getString(2)+"</td><td>"+resultSet.getInt(3)+"</td></tr>");
		    }
			DatabaseUtility.closeConnection(connection);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
