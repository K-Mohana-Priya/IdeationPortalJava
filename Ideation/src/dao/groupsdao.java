package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DatabaseUtility;
import Beans.groupsbean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class groupsdao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
public groupsbean getGroupbyName(String Name) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("select * from groups where group_name=?");
	preparedStatement.setString(1, Name);
	resultSet = preparedStatement.executeQuery();
	groupsbean group = resultSet.next() ? new groupsbean(resultSet.getInt(1),
			resultSet.getString(2),resultSet.getInt(3)) : null;
	DatabaseUtility.closeConnection(connection);
	return group;
}
public void createGroup(String group_name) throws SQLException{
	int nou=0;
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("insert into groups(group_name,no_of_users) values(?,?)");
	preparedStatement.setString(1, group_name);
	preparedStatement.setInt(2,nou );
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
}
public void changeCount(int group_id,int no_of_users) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("update groups set no_of_users=? where group_id=?");
	preparedStatement.setInt(1, no_of_users );
	preparedStatement.setInt(2, group_id);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
}
public void deleteGroupById(int group_id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("delete from groups where group_id=?");
	preparedStatement.setInt(1, group_id);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
}
}
