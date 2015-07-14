package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DatabaseUtility;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class messagedao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	public void insertMessage(String user_id, String mail,String data) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		String send_to=null;
		PreparedStatement ps1 = (PreparedStatement) connection
				.prepareStatement("select user_id from user where email=?");
		ps1.setString(1, mail);
		resultSet=ps1.executeQuery();
		if(resultSet.next()){
			send_to=resultSet.getString(1);
		}
		String sql="insert into message(message_data,user_id,sending_user_id) values('"+data+"','"+send_to+"','"+user_id+"')";
		preparedStatement = (PreparedStatement) connection
				.prepareStatement(sql);
		preparedStatement.execute();
		DatabaseUtility.closeConnection(connection);
	}
public int getMessageCount(String user_id) throws SQLException
{
	int count=0;
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("select count(*) from message where user_id=?");
	preparedStatement.setString(1, user_id);
	resultSet = preparedStatement.executeQuery();
	if(resultSet.next())
	{
		count=resultSet.getInt(1);
	}
	DatabaseUtility.closeConnection(connection);
	return count;
}
public void deleteMessagebyUId(String id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("delete from message where user_id=?");
	preparedStatement.setString(1, id);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
}
}
