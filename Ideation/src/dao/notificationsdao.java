package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DatabaseUtility;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class notificationsdao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	public void insertNotification(String user_id,String data) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("insert into notifications(user_id,notification_data) values(?,?)");
		preparedStatement.setString(1, user_id);
		preparedStatement.setString(2, data);
		preparedStatement.execute();
		System.out.println("notification Works");
		DatabaseUtility.closeConnection(connection);
	}
public int getNotificationCount(String user_id) throws SQLException
{
	int count=0;
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("select count(*) from notifications where user_id=?");
	preparedStatement.setString(1, user_id);
	resultSet = preparedStatement.executeQuery();
	if(resultSet.next())
	{
		count=resultSet.getInt(1);
	}
	DatabaseUtility.closeConnection(connection);
	return count;
}

public void deleteNotificationsbyUId(String id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("delete from notifications where user_id=?");
	preparedStatement.setString(1, id);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
}
public void deleteNotificationbyId(int id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("delete from notifications where notification_id=?");
	preparedStatement.setInt(1, id);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
}
}
