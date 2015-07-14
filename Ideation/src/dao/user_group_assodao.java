package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DatabaseUtility;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class user_group_assodao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	public void deleteUserGroupbyUId(String id) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("delete from user_group_asso where user_id=?");
		preparedStatement.setString(1, id);
		preparedStatement.execute();
		DatabaseUtility.closeConnection(connection);
	}
	public void addUserToGroup(String user_id,int group_id) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("insert into user_group_asso values(?,?)");
		preparedStatement.setString(1,user_id);
		preparedStatement.setInt(2,group_id);
		preparedStatement.execute();
		DatabaseUtility.closeConnection(connection);
	}
	public void removeUserFromGroup(String user_id,int group_id) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("delete from user_group_asso where user_id=? and group_id=?");
		preparedStatement.setString(1,user_id);
		preparedStatement.setInt(2,group_id);
		preparedStatement.execute();
		DatabaseUtility.closeConnection(connection);
	}
	public String getUserGroupbyUId(String id) throws SQLException
	{
		String grps="";
		user_groupdao user_g = new user_groupdao();
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("select group_id from user_group_asso where user_id=?");
		preparedStatement.setString(1, id);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			System.out.println("Group id"+resultSet.getInt(1));
			grps+=user_g.getUserGroupbyGId(resultSet.getInt(1));
			if(!resultSet.isLast()){
				grps+=" ";
			}
		}
		DatabaseUtility.closeConnection(connection);
		return grps;
	}
}
