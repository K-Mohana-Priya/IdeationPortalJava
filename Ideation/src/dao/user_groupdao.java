package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DatabaseUtility;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class user_groupdao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	public String getUserGroupbyGId(int id) throws SQLException
	{
		String grp_name =null;
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("select group_name from groups where group_id=?");
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
		{
			grp_name=resultSet.getString(1);
		}
		DatabaseUtility.closeConnection(connection);
		System.out.println("Group name"+grp_name);
		return grp_name;
	}
}
