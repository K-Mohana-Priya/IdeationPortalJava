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
 * Servlet implementation class displayusersingroupcontroller
 */
@WebServlet("/displayusersingroupcontroller")
public class displayusersingroupcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayusersingroupcontroller() {
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
			int gid=Integer.parseInt(request.getParameter("val"));
			connection = DatabaseUtility.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement("select * from user where user_id in (select user_id from user_group_asso where group_id=?)");
			preparedStatement.setInt(1, gid);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
					out.print("<tr><td>"+resultSet.getString(1)+"</td><td>"+resultSet.getString(2)+"</td><td>"+resultSet.getInt(6)+"</td><td>"+resultSet.getInt(7)+"</td><td>"+resultSet.getInt(8)+"</td></tr>");
		    }
			DatabaseUtility.closeConnection(connection);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
