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
 * Servlet implementation class showuserstoremovecontroller
 */
@WebServlet("/showuserstoremovecontroller")
public class showuserstoremovecontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showuserstoremovecontroller() {
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
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try{
			int s=Integer.parseInt(request.getParameter("val"));
			connection = DatabaseUtility.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("select user_id from user_group_asso where group_id=? ");
			preparedStatement.setInt(1,s);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
					out.print("<option>"+resultSet.getString(1)+"</option>");
		    }
			DatabaseUtility.closeConnection(connection);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
