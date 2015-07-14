package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DatabaseUtility;
import Beans.user_rolebean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class user_roledao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
public user_rolebean getRolebyUId(String id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("select * from user_role where user_id=?");
	preparedStatement.setString(1, id);
	resultSet = preparedStatement.executeQuery();
	user_rolebean user = resultSet.next() ? new user_rolebean(resultSet.getString(1),
			resultSet.getInt(2)) : null;
	DatabaseUtility.closeConnection(connection);
	return user;
}
public void deleteUserRolebyUId(String id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("delete from user_role where user_id=?");
	preparedStatement.setString(1, id);
	preparedStatement.execute();
	
	DatabaseUtility.closeConnection(connection);
}
public void setUserRolebyUId(String user_id,String role_id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("insert into user_role values(?,?)");
	preparedStatement.setString(1, user_id);
	preparedStatement.setString(2, role_id);
	preparedStatement.execute();

	preparedStatement = (PreparedStatement) connection
			.prepareStatement("select user_id from jforum_users where username=?");
	preparedStatement.setString(1, user_id);
	resultSet = preparedStatement.executeQuery();
	int frole=1,fuid=1;
	if(resultSet.next())
	{
		fuid=resultSet.getInt(1);
		if(role_id.equals("2"))
			frole=2;
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("INSERT INTO jforum_user_groups (group_id, user_id) VALUES (?,?)");
		preparedStatement.setInt(1, frole);
		preparedStatement.setInt(2, fuid);
		preparedStatement.execute();
	}
	DatabaseUtility.closeConnection(connection);
}
}
